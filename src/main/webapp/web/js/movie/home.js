/* App Module */
var modelApp = angular.module('modelApp', []);

modelApp.config(function($interpolateProvider) {
	$interpolateProvider.startSymbol('[[');
	$interpolateProvider.endSymbol(']]');
});
function modelListCtrl ($scope, $http) {
	

	function init() {

	}

	
	init();
}  


