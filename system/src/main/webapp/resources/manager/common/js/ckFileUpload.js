$(function(){
	//	notice write/mod
	
	var ckeditorEvt = null;
	if(document.getElementById('ckeditor') != null){
		
		var ckeditorEvt = CKEDITOR.replace('ckeditor', 
				{
					filebrowserUploadUrl:'../file/imageUpload'
				}
		);	
		
		
		
		//	업로드 탭으로 시작; 자세히탭 제거 링크탭 제거
		CKEDITOR.on('dialogDefinition', function (ev) {

            var dialogName = ev.data.name;
            var dialog = ev.data.definition.dialog;

            var dialogDefinition = ev.data.definition;

            if (dialogName == 'image') {
				
                dialog.on('show', function (obj) {
                    this.selectPage('Upload'); 				//업로드텝으로 시작
                });

                dialogDefinition.removeContents('advanced'); // 자세히탭 제거

                dialogDefinition.removeContents('Link'); 	// 링크탭 제거

            }
        });//	업로드 탭으로 시작; 자세히탭 제거 링크탭 제거			
		
	
	
		//	파일 올리기 전 이벤트
		ckeditorEvt.on( 'fileUploadRequest', function( evt ) {
			var fileLoader = evt.data.fileLoader;
			//console.log(evt.data.requestData);
			
			var _uploadErrorUrl = '../file/uploadError';
			
			var _fileName = evt.data.requestData.upload.file.name;
			var _fileSize = evt.data.requestData.upload.file.size;
			
			if(_fileSize >10485760){
				//	10mb 보다 용량이 큰 경우
				alert('10MB 이하의 이미지를 올려주세요.');	//	evt.stop() 및 evt.cancle() 하면 fileUploadRequest 이벤트 먹히지 않음..
				
				fileLoader.xhr.open( 'POST', _uploadErrorUrl, true );
				evt.data.requestData.upload = '';	//	초기화
				
				evt.data.requestData.fileName = _fileName;
				evt.data.requestData.fileSize = _fileSize;
				evt.data.requestData.message = '10MB 이하의 이미지를 올려주세요.'; 
				
				
			}else{
				if(!enernet.modules.utils.isImgExt(_fileName)){
					//	이미지 파일이 아닌경우
					alert(_fileName + ' 은 이미지 파일이 아닙니다');	//	evt.stop() 및 evt.cancle() 하면 fileUploadRequest 이벤트 먹히지 않음..
					
					fileLoader.xhr.open( 'POST', _uploadErrorUrl, true );
					evt.data.requestData.upload = '';	//	초기화
					
					evt.data.requestData.fileName = _fileName;
					evt.data.requestData.fileSize = _fileSize;
					evt.data.requestData.message = _fileName + ' 은 이미지 파일이 아닙니다';
					
				}else{
					//	이미지 파일인 경우
					fileLoader.xhr.open( 'POST', fileLoader.uploadUrl, true );
					evt.data.requestData.upload = { file: fileLoader.file, name: fileLoader.fileName };
				}
			}
		});//
		
		
		//	파일 이미지 올린 후 이벤트
		ckeditorEvt.on( 'fileUploadResponse', function( evt ) {
		    
		    evt.stop();
		    // Get XHR and response.
		    var data = evt.data,
		        xhr = data.fileLoader.xhr,
		        response = xhr.responseText.split( '|' );
		    
		    			    
			//	response = 값|값| 값|
			//	console.log(response);								
			if(response[0]=="0"){
				data.message = response[1];
			}else{
				//console.log(response)
				data.url = response[2];
				data.message = response[1];
				//evt.cancel();
			}
	        
		});//	파일 이미지 올린 후 이벤트 종료
		
	
	};//
	
})