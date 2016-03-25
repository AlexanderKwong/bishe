/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.config(function($interpolateProvider) {
	$interpolateProvider.startSymbol('[[');
	$interpolateProvider.endSymbol(']]');
});
function modelListCtrl ($scope, $http) {
	

	$scope.queryGrade=function(){
		var params="";
		base.connectService('/examvideo/queryGrade',params, function(data){
			$scope.gradeList=data.gradeList;
			
			if($scope.grade==""){
				$scope.grade=data.gradeList[0].GRADE_ID;
			}
//			$scope.query();
		}, function(data){});
	}
	
	$scope.querySubject=function(){
		var params="";
		base.connectService('/examvideo/querySubject',params, function(data){
			$scope.subjectList=$scope.sortTipspaper(data.subjectList);
//			for(var tipIndex = 0; tipIndex<$scope.subjectList.length; tipIndex++){
//				if( $scope.subjectList[tipIndex].SUBJECT_ID == "YW" ){
//					$scope.subject=$scope.subjectList[tipIndex].SUBJECT_ID;
//					break;
//				}
//			}
			

//			$scope.query();
		}, function(data){});
	}
	$scope.query=function(){
		if($scope.grade==""){
			$scope.grade="c1";
		}
		if($scope.subject==""){
			$scope.subject="YW";
		}
		$scope.courseList=[];
		var params="grade="+$scope.grade+"&subject="+$scope.subject;
//		alert(params);
		base.connectService('/examvideo/query',params, function(data){
			$scope.courseList=data.courseList;

		}, function(data){});
	}
	
	$scope.showVideo=function(course){
		$scope.videoname=course.videoName;
//		var player=null;
//		player =polyvObject('#videoPlayer').videoPlayer({
//			'width':'100%',
//			'height':'300',
//			'vid' : course.polyvId,
//			'flashvars':{'autoplay':'true'}
//		});
//		$('#playvideo').modal('show');
//		alert(course.polyvId);
		$("#videoIframe").attr("src","/examvideo/polyv?vid="+course.polyvId);
		$('#playvideo').modal('show');
		
//		var params="vid="+course.polyvId;
////		alert(params);
//		base.connectService('/examvideo/polyv',params, function(data){
//			$("#videoIframe").attr("src",data.url);
//			$('#playvideo').modal('show');
//
//		}, function(data){});
	}
	
	
	$scope.sortTipspaper = function(tipspaper){
		for(var tipIndex = 0; tipIndex<tipspaper.length; tipIndex++){
			if( tipspaper[tipIndex].SUBJECT_ID == "YW" ){
				tipspaper[tipIndex].subjectSort = 1;
			}else if( tipspaper[tipIndex].SUBJECT_ID == "SX" ){
				tipspaper[tipIndex].subjectSort = 2;
			}else if( tipspaper[tipIndex].SUBJECT_ID == "YY" ){
				tipspaper[tipIndex].subjectSort = 3;
			}else if( tipspaper[tipIndex].SUBJECT_ID == "WL" ){
				tipspaper[tipIndex].subjectSort = 4;
			}else if( tipspaper[tipIndex].SUBJECT_ID == "HX" ){
				tipspaper[tipIndex].subjectSort = 5;
			}else if( tipspaper[tipIndex].SUBJECT_ID == "ZZ" ){
				tipspaper[tipIndex].subjectSort = 6;
			}else if( tipspaper[tipIndex].SUBJECT_ID == "LS" ){
				tipspaper[tipIndex].subjectSort = 7;
			}else if( tipspaper[tipIndex].SUBJECT_ID == "DL" ){
				tipspaper[tipIndex].subjectSort = 8;
			}else if( tipspaper[tipIndex].SUBJECT_ID == "SW" ){
				tipspaper[tipIndex].subjectSort = 9;
			}else if( tipspaper[tipIndex].SUBJECT_ID == "WZ" ){
				tipspaper[tipIndex].subjectSort = 10;
			}else if( tipspaper[tipIndex].SUBJECT_ID == "LZ" ){
				tipspaper[tipIndex].subjectSort = 11;
			}else{
				tipspaper[tipIndex].subjectSort = 999;
			}
		}
		return tipspaper;
	}

	//初始化操作
	var base=null;
	function init() {
		// 生成base类实例
		base = new Base($http);
//		$scope.grade="c1";
//		$scope.subject="YW";
		$scope.grade=$("#grade").val();
		$scope.subject=$("#subject").val();
		var player=null;
//		alert(grade+" *  "+subject);
		$scope.queryGrade();
		$scope.querySubject();
//		if($("#grade").val()==""){
//			$scope.queryGrade();
//			
//		}
//		
//		if($("#subject").val()==""){
//			$scope.querySubject();
//		}
		$scope.query();
		
		$('#playvideo').on('hidden.bs.modal', function (e) {
			  // do something...
			$("#videoIframe").attr("src","#");
//			alert($("#videoIframe").attr("src"));
//			player=null;
//			$("#videoPlayer").html();
			})
	}


	init();
	
}  


