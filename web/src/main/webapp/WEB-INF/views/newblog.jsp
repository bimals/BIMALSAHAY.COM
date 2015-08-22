<div ng-view></div>
<!-- 
Page Content
<div class="container">

    <div class="row">

        <div class="col-lg-12">
            <h1 class="page-header">Add New Blog
                <small>Express Yourself</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#/">Home</a></li>
                <li class="active">New Blog</li>
            </ol>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4">
            <h3>BIMALSAHAY.COM</h3>
            <h4>Software Engineer</h4>

            <p>
                400 E Remington Drive, C125<br>
                Sunnyvale, CA 94087<br>
            </p>

            <p><i class="fa fa-phone"></i> <abbr title="Phone">P</abbr>: (415) 579-8091</p>

            <p><i class="fa fa-envelope-o"></i> <abbr title="Email">E</abbr>: <a
                    href="mailto:feedback@startbootstrap.com">feedback@bimalsahay.com</a></p>

            <p><i class="fa fa-clock-o"></i> <abbr title="Hours">H</abbr>: Monday - Friday: 9:00 AM to 5:00 PM</p>
            <ul class="list-unstyled list-inline list-social-icons">
                <li class="tooltip-social facebook-link"><a href="#facebook-page" data-toggle="tooltip"
                                                            data-placement="top" title="Facebook"><i
                        class="fa fa-facebook-square fa-2x"></i></a></li>
                <li class="tooltip-social linkedin-link"><a href="#linkedin-company-page" data-toggle="tooltip"
                                                            data-placement="top" title="LinkedIn"><i
                        class="fa fa-linkedin-square fa-2x"></i></a></li>
                <li class="tooltip-social twitter-link"><a href="#twitter-profile" data-toggle="tooltip"
                                                           data-placement="top" title="Twitter"><i
                        class="fa fa-twitter-square fa-2x"></i></a></li>
                <li class="tooltip-social google-plus-link"><a href="#google-plus-page" data-toggle="tooltip"
                                                               data-placement="top" title="Google+"><i
                        class="fa fa-google-plus-square fa-2x"></i></a></li>
            </ul>
        </div>
        <div class="col-sm-8">
<form role="form" id="js-upload-form" method="post" ng-controller = "myCtrl" enctype="multipart/form-data">
    <div class="form-group">
                <label class="control-label">Title</label>
                <input type="text" class="form-control" name="title" ng-model="blogTitle"/>
    </div>

    <div class="form-group">
        <div class="row">
            <div class="col-xs-6">
                <label class="control-label">Web Link</label>
                <input type="text" class="form-control" name="website" ng-model="blogWebsite"/>
            </div>

            <div class="col-xs-6">
                <label class="control-label">Youtube link</label>
                <input type="text" class="form-control" name="trailer" ng-model="blogYouTube"/>
            </div>
        </div>
    </div>

    <div class="form-group">
    	<label class="control-label">Image That Defines Your Blog</label>
		<span class="btn btn-default btn-file"><input type="file" file-model="myFile"/>
		</span>
       <div class="panel panel-default">
        <div class="panel-heading"><strong>Upload Files</strong> <small>Image That Defines Your Blog</small></div>
        <div class="panel-body">

          Standar Form
          <h4>Select files from your computer</h4>
          <form action="" method="post" enctype="multipart/form-data" >

          </form>
            <div class="form-inline">
              <div class="form-group">
                <input type="file" name="files[]" id="js-upload-files" multiple file-model="myFile">
              </div>
              <button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit">Upload files</button>
            </div>
          Drop Zone
          <h4>Or drag and drop files below</h4>
          <div class="upload-drop-zone" id="drop-zone">
            Just drag and drop files here
          </div>

          Progress Bar
          <div class="progress">
            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
              <span class="sr-only">60% Complete</span>
            </div>
          </div>

          Upload Finished
          <div class="js-upload-finished">
            <h3>Processed files</h3>
            <div class="list-group">
              <a href="#" class="list-group-item list-group-item-success"><span class="badge alert-success pull-right">Success</span>image-01.jpg</a>
              <a href="#" class="list-group-item list-group-item-success"><span class="badge alert-success pull-right">Success</span>image-02.jpg</a>
            </div>
          </div>
        </div>
      </div>		
    </div>


    <div class="form-group">
        <label class="control-label">Write More</label>
        <textarea class="form-control" name="review" rows="8" ng-model="blogText"></textarea>
    </div>
  <div id="mode-group" class="btn-group" data-toggle="buttons">
    <label class="btn btn-default">
      <input type="radio" name="mode" id="option1"> Preview
    </label>
    <label class="btn btn-default">
      <input type="radio" name="mode" id="option2"> Save Draft
    </label>
  </div>
    <button class="btn btn-default btn-primary" ng-click="uploadFile()"> Publish </button>
</form>

<script>
+function($) {
    'use strict';

    var dropZone = document.getElementById('drop-zone');
    var uploadForm = document.getElementById('js-upload-form');

    var startUpload = function(files) {
        console.log(files)
        
                
        for (var i = 0; i < files.length; i++) { 
		    $('.list-group').append('<a href="#" class="list-group-item list-group-item-success"><span class="badge alert-success pull-right">Success</span>' + files[i].name+ '</a>');
		}
    }

    uploadForm.addEventListener('submit', function(e) {
        var uploadFiles = document.getElementById('js-upload-files').files;
        e.preventDefault()
		
        startUpload(uploadFiles)
    })

    dropZone.ondrop = function(e) {
        e.preventDefault();
        this.className = 'upload-drop-zone';

        startUpload(e.dataTransfer.files)
    }

    dropZone.ondragover = function() {
        this.className = 'upload-drop-zone drop';
        return false;
    }

    dropZone.ondragleave = function() {
        this.className = 'upload-drop-zone';
        return false;
    }

}(jQuery);
</script> -->