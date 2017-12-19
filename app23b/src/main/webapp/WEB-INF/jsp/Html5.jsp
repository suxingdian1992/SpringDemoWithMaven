<!DOCTYPE HTML>
<html>
<head>
<script>
    var totalFileLength, totalUploaded, fileCount, filesUploaded;

    function debug(s) {
        var debug = document.getElementById('debug');
        if (debug) {
            debug.innerHTML = debug.innerHTML + '<br/>' + s;
        }
    }

    function onUploadComplete(e) {
        totalUploaded += document.getElementById('files').
                files[filesUploaded].size;
        filesUploaded++;
        debug('complete ' + filesUploaded + " of " + fileCount);
        debug('totalUploaded: ' + totalUploaded);        
        if (filesUploaded < fileCount) {
            uploadNext();
        } else {
            var bar = document.getElementById('bar');
            bar.style.width = '100%';
            bar.innerHTML = '100% complete';
            alert('Finished uploading file(s)');
        }
    }
    
    function onFileSelect(e) {
        var files = e.target.files; // FileList object
        var output = [];
        fileCount = files.length;
        totalFileLength = 0;
        for (var i=0; i<fileCount; i++) {
            var file = files[i];
            output.push(file.name, ' (',
                  file.size, ' bytes, ',
                  file.lastModifiedDate.toLocaleDateString(), ')'
            );
            output.push('<br/>');
            debug('add ' + file.size);
            totalFileLength += file.size;
        }
        document.getElementById('selectedFiles').innerHTML = 
            output.join('');
        debug('totalFileLength:' + totalFileLength);
    }

    function onUploadProgress(e) {
        if (e.lengthComputable) {
            var percentComplete = parseInt(
                    (e.loaded + totalUploaded) * 100 
                    / totalFileLength);
            var bar = document.getElementById('bar');
            bar.style.width = percentComplete + '%';
            bar.innerHTML = percentComplete + ' % complete';
        } else {
            debug('unable to compute');
        }
    }

    function onUploadFailed(e) {
        alert("Error uploading file");
    }
    
    function uploadNext() {
        var xhr = new XMLHttpRequest();
        var fd = new FormData();
        var file = document.getElementById('files').
                files[filesUploaded];
        fd.append("multipartFile", file);//把文件追加到formdata中
        //更新进度条
        xhr.upload.addEventListener(
                "progress", onUploadProgress, false);
        //上传完成时
        xhr.addEventListener("load", onUploadComplete, false);
        //上传失败时
        xhr.addEventListener("error", onUploadFailed, false);
        xhr.open("POST", "file_upload");
        debug('uploading ' + file.name);
        xhr.send(fd);
    }

    function startUpload() {
        totalUploaded = filesUploaded = 0;
        uploadNext();
    }
    window.onload = function() {
    	/* 监听当文件选择改变的时候，修改选择文件框里的显示 */
        document.getElementById('files').addEventListener(
                'change', onFileSelect, false);
        /* 把开始山村乖哈un按钮的点击事件和开始上传事件连接在一起，当点击的时候出发上传事件 */
        document.getElementById('uploadButton').
                addEventListener('click', startUpload, false);
    }
</script>
</head>
<body>
<h1>Multiple file uploads with progress bar</h1>
<div id='progressBar' style='height:20px;border:2px solid green'>
    <div id='bar' 
            style='height:100%;background:#33dd33;width:0%'>
    </div>
</div>
<form>
    <input type="file" id="files" multiple/>
    <br/>
    <output id="selectedFiles"></output>
    <input id="uploadButton" type="button" value="Upload"/>
</form>
<div id='debug' 
    style='height:100px;border:2px solid green;overflow:auto'>
</div>
</body>
</html>