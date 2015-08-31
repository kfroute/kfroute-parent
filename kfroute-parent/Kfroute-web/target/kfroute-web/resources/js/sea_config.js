var domain = 'http://localhost:8083/';
seajs.config({
	alias: {
		'jquery': domain+'/assets/js/libs/jquery/jquery-1.11.0.min',
		'template': domain+'/assets/js/libs/template',
		'bootstrap': domain+'/assets/js/libs/bootstrap/js/bootstrap.min',
		'md5': domain+'/assets/js/libs/md5',
		'app': domain+'/assets/js/app',
		'area': domain+'/assets/js/libs/area',
		
		// plugins
		'upload': domain+'/assets/js/libs/upload',
		'ueditor': domain+'/assets/js/libs/ueditor/main',
		'customform': domain+'/assets/js/libs/customform',
		'datetimepicker': domain+'/assets/js/libs/datetimepicker',
		'panelslider': domain+'/assets/js/libs/jquery/jquery.panelslider',
		'bv': domain+'/assets/js/libs/bv',
		'scrollbar': domain+'/assets/js/libs/jquery/scrollbar/scrollbar',
		'charts': domain+'/assets/js/libs/charts',
		'qrcode': domain+'/assets/js/libs/qrcode',
		'baidu_map': 'http://api.map.baidu.com/getscript?v=2.0&ak=PDpIPmbZCBj0q9i1rg91vTKM&services=&t=20140617153133',
		
		'conf': domain + '/tpl/config',
		'lbs_conf': domain + '/tpl/lbs_config',
		'plugin_conf': domain + '/plugin/config',
		// module
		'menu_conf': domain+'/assets/js/models/menu_conf',
		'user': domain+'/assets/js/models/user/main',
		'site': domain+'/assets/js/models/site/main',
		'site_build': domain+'/assets/js/models/site/build',
		'member': domain+'/assets/js/models/member/main',
		'lbs': domain+'/assets/js/models/lbs/main',
		'public': domain+'/assets/js/models/public/main',
		'public_app': domain+'/assets/js/models/public/app',
		'public_menu': domain+'/assets/js/models/public/menu',
		'public_reply': domain+'/assets/js/models/public/reply',
		'public_resource': domain+'/assets/js/models/public/resource',
		'public_pay': domain+'/assets/js/models/public/pay',
		'public_stats': domain+'/assets/js/models/public/stats',
		'public_multinews': domain+'/assets/js/models/public/multinews',
		'public_user': domain+'/assets/js/models/public/user',
		'public_qrcode': domain+'/assets/js/models/public/qrcode',
		'public_service': domain+'/assets/js/models/public/service',
		'public_sns': domain+'/assets/js/models/public/sns',
		'activity': domain+'/assets/js/models/activity/main',
		'coupon': domain+'/assets/js/models/coupon/main',
		'restaurant': domain+'/assets/js/models/restaurant/main',
		'food': domain+'/assets/js/models/food/main',
		'tuangou': domain+'/assets/js/models/tuangou/main',
		'fullview': domain+'/assets/js/models/fullview/main',
		'interact': domain+'/assets/js/models/interact/main',
		'hongbao': domain+'/assets/js/models/hongbao/main',
		'shop':domain+'/assets/js/models/shop/main',
		'newshop':domain+'/assets/js/models/newshop/main',
		'form':domain+'/assets/js/models/form/main',
		'kf': domain+'/assets/js/models/kf/main',
		'reservation': domain+'/assets/js/models/reservation/main',
		'printer': domain+'/assets/js/models/device/printer',
		'wifi': domain+'/assets/js/models/device/wifi',
		'news': domain+'/assets/js/models/news/main',
		'shake':domain+'/assets/js/models/shake/main',
		'car':domain+'/assets/js/models/car/main',
		'wedding': domain+'/assets/js/models/wedding/main',
		'heka': domain+'/assets/js/models/heka/main',
		'educ': domain+'/assets/js/models/educ/main',
		'scene': domain+'/assets/js/models/scene/main',
		'hotel': domain+'/assets/js/models/hotel/main',
		'ahb': domain+'/assets/js/models/ahb/main',
		'ajrq': domain+'/assets/js/models/ajrq/main',
		'game': domain+'/assets/js/models/game/main',
		'brainstorm': domain+'/assets/js/models/brainstorm/main',
		'house': domain+'/assets/js/models/house/main',
		'medical': domain+'/assets/js/models/medical/main',
		'wall': domain+'/assets/js/models/wall/main',
		'store' : domain+'/assets/js/models/store/store',
		'store_user' : domain+'/assets/js/models/store/store_user',
		'store_restaurant' : domain+'/assets/js/models/store/store_restaurant',
		'store_food' : domain+'/assets/js/models/store/store_food',
	},
	map: [
		[ /^(.*\.(?:css|js|tpl))(.*)$/i, '$1?' + (_version || '') ]
	],
	preload: ['bootstrap'],
	debug: true,
	base: '/',
	charset: 'utf-8'
});