$(function() {

	//	이미지 팝업
	//	pop-show
	var _popImgEvt = null;
	
	if(document.getElementsByClassName('thumb-evt').length != 0){
		
		_popImgEvt = document.getElementsByClassName('thumb-evt');
		
		for(var i=0; i<_popImgEvt.length; i++){
	
			_popImgEvt[i].onclick = function(){
				
				var _imgSrc = $(this).attr("data-1");
				
				
				if(!enernet.modules.utils.isImgExt(_imgSrc)){
					return alert('이미지 파일이 없습니다');
				}				
				 
				var image = new Image();
				image.src = _imgSrc;
				
				var viewer = new Viewer(image, 
					{ 
						hidden: function () {
							
							viewer.destroy();
						},
						toolbar: {
							zoomIn: 4,
							zoomOut: 4,
							oneToOne: 4,
							reset: 4,
							prev: 0,
						    play: {
						    	show: 4,
						    	size: 'large'
						    },
						    next: 0,
						    rotateLeft: 4,
						    rotateRight: 4,
						    flipHorizontal: 4,
						    flipVertical: 4,
						},
						navbar : 0,
						tooltip : true,
						viewed : function(evt){
							evt.detail.image.onclick = function(){
								
								
							}
						}
						
						
					}
				);
		
				viewer.show();
				
			}
		}
		
	};
	
});