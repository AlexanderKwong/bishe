'use strict';
var modelApp, deps;

deps = ['angularBootstrapNavTree','ngRoute'];

if (angular.version.full.indexOf("1.2") >= 0) {
  deps.push('ngAnimate');
}

modelApp = angular.module('modelApp', deps);

modelApp.config(function($interpolateProvider) {
	$interpolateProvider.startSymbol('[[');
	$interpolateProvider.endSymbol(']]');
});

//判断浏览器类型和版本
var userAgent = navigator.userAgent.toLowerCase();
//Figure out what browser is being used 
jQuery.browser = {
  version: (userAgent.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1],
  safari: /webkit/.test(userAgent),
  opera: /opera/.test(userAgent),
  msie: /msie/.test(userAgent) && !/opera/.test(userAgent),
  mozilla: /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent)
};


/* 自定义过滤器 */
modelApp.filter('subject', function() {
	return function(input) {
		if(input=="YW"){
			input="语文";
		}else if(input=="SX"){
			input="数学";
		}else if(input=="YY"){
			input="英语";
		}else if(input=="WL"){
			input="物理";
		}else if(input=="HX"){
			input="化学";
		}else if(input=="SW"){
			input="生物";
		}else if(input=="DL"){
			input="地理";
		}else if(input=="ZZ"){
			input="政治";
		}else if(input=="LS"){
			input="历史";
		}else if(input=="JK"){
			input="健康";
		}else if(input=="KX"){
			input="科学";
		}else if(input=="XX"){
			input="信息";
		}else if(input=="TY"){
			input="体育";
		}else if(input=="MU"){
			input="音乐";
		}else if(input=="MS"){
			input="美术";
		}else if(input=="SF"){
			input="书法";
		}else if(input=="SP"){
			input="思想品德";
		}else if(input=="WZ"){
			input="文科综合";
		}else if(input=="LZ"){
			input="理科综合";
		}	
		return input;
	}

});
/**
 * 
 * @param $scope
 * @param $http
 * @param $timeout
 * @param isHasRight 若在会员有效期，==true 
 * @param freeExp 若不为null,则是免费体验信息
 */
function modelListCtrl($scope, $http,$timeout) {
	
	
	$scope.query=function(){
		reportDataAnalysisServices.queryUserInfo(g_userid, function(data){
			$scope.grade=data.grade ;
			lag=data.lag;
			if($scope.grade=="c1"){
				$scope.showvideo('2929ca8d3049cc552fbab4b05d369f5a_2');
			}else if($scope.grade=="c2"){
				$scope.showvideo('2929ca8d30939d740f56afa3dace444a_2');
			}
			updateEndTime();
		}, function(data){});	
	}
	
	$scope.showvideo=function(vid){
		if(player!=null){
			player=null;
		}
		player = polyvObject('#plv_id').videoPlayer({
			'width':'auto',
			'height':'200',
			'vid' : vid
		});
	}
	
	$scope.showupleve=function(){
		$scope.upleve=true;
		$scope.share=false;
	}
	$scope.showshare=function(){
		$scope.upleve=false;
		$scope.share=true;
	}
	var reportDataAnalysisServices;
	var player=null;
	var lag=0;
	function init() {
		
		reportDataAnalysisServices = new ReportDataAnalysisServices($http);
		$scope.query();
		
		
	}

	init();
	
	function updateEndTime()
	{
		 var isreload=false;
		 var lag2=lag/1000;
		 if(lag2 > 0){
			   var second = Math.floor(lag2 % 60);     
			   var minite = Math.floor((lag2 / 60) % 60);
			   var hour = Math.floor((lag2 / 3600) % 24);
			   var day = Math.floor((lag2 / 3600) / 24);
			   var showTime="有效期：";
			   if(day>0){
				   showTime=showTime+day+"天";
			   }
			   if(hour>0){
				   showTime=showTime+hour+"小时";
			   }
			   if(minite>0){
				   showTime=showTime+minite+"分";
			   }
			   showTime=showTime+second+"秒";
			   $scope.timeth=showTime;
//			   $("#timeth").html(showTime);
			   
			 
		lag=lag-1000;
		  } else{
			  
			  isreload=true;
			   
			  
		  }

//		   alert(isreload);
	     //到时后显示内容
		 if(isreload==true){
			 $scope.timeth="敬请期待！";
		 }
		 else{
			 return $timeout(function() {
				 updateEndTime();
			 }, 1000);
		 }
	}
}