/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.config(function($interpolateProvider) {
	$interpolateProvider.startSymbol('[[');
	$interpolateProvider.endSymbol(']]');
});
function modelListCtrl ($scope, $http) {
	

	$scope.queryWrongTest=function(target){
		var params="UserId="+$("#UserId").val();
		base.connectService('/examvideo/wrongTest',params, function(data){
			if(data.success){
				window.location=data.url;
			}else{
				
				$('#nowrong').modal('show');
//				alert("暂时没有考试记录");
			}
			
//			$scope.query();
		}, function(data){},target);
	};
	$scope.queryCswPath=function(cswname,index,order){
		$scope.videoname=cswname;
		var params="UserId="+$("#UserId").val()+"&index="+index+"&order="+order;
		base.connectService('/examvideo/cswPath',params, function(data){
			$("#videoIframe").attr("src",data.path);
			$('#playvideo').modal('show');
//			$scope.query();
		}, function(data){});
	};

	//初始化操作
	var base=null;
	function init() {
		// 生成base类实例
		base = new Base($http);
		$('#playvideo').on('hidden.bs.modal', function (e) {
			  // do something...
//			$("#videoIframe").attr("src","");
			$("#videoIframe").attr("src","/weixin/images/loading.gif");
//			alert($("#videoIframe").attr("src"));
//			player=null;
//			$("#videoPlayer").html();
			});
	}


	init();
	
}  


