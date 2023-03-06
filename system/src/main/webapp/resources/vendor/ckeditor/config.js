/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	//	높이 크기
	config.height = '450px';
	

	config.toolbar =
		[
			{ name: 'document', items: [ '', '-', '', 'NewPage', 'Preview', 'Print', '-', 'Templates' ] },
			{ name: 'clipboard', items: [ '-', '-', '-', '-', '-', '-', 'Undo', 'Redo' ] },
			{ name: 'editing', items: [ 'Find', 'Replace', '-', 'SelectAll', '-', '-' ] },
			{ name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'CopyFormatting', 'RemoveFormat' ] },
			
			{ name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote',  '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl'] },
			{ name: 'insert', items: [ 'Image',  'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak' ] },
			'/',
			{ name: 'styles', items: [ 'Styles', 'Format', 'Font', 'FontSize' ] },
			{ name: 'colors', items: [ 'TextColor', 'BGColor' ] },
			{ name: 'tools', items: [ 'Maximize', 'ShowBlocks' ] }
		];
		
};



/*
config.toolbar =
	[
		[ 'NewPage','Preview' ],
		[ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ],
		[ 'Find','Replace','-','SelectAll','-','Scayt' ],
		[ 'Image','Table','HorizontalRule','SpecialChar','PageBreak'],
		[ 'Styles','Format' ],
		[ 'Bold','Italic','Strike','-','RemoveFormat' ]
	];
	*/
