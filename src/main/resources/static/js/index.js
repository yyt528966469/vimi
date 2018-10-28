$(function(){	


			$('.list-box .list-box-1 span').click(function(){
			$(this).css("background","none");
			// $(this).addClass('current');

					});

		$('.list-box span').click(function(){
			$(this).parent().toggleClass('current').siblings().removeClass('current');
			// $(this).addClass('current');

					});








	$('list-box-1 ul li').click(function(){
			$('.center .center-1 ').css("display","block");
			$('.center .center-2 ').css("display","none");
	});

	$('list-box-2 ul li').click(function(){
			$('.center .center-2 ').css("display","block");
			$('.center .center-1 ').css("display","none");
	});

$('.list-box ul li').click(function(){
	$(this).css("color","white").siblings().css("color","#a7c8db");
});
/*$('.list-box-2 ul li:eq(0)').click(function(){

	$(this).css("color","white").siblings().css("color","#a7c8db");
	$('.list-box-3').css("color","#a7c8db");
	$('.list-box-4').css("color","#a7c8db");
	$('.list-box-5').css("color","#a7c8db");
	$('.list-box-6').css("color","#a7c8db");
})*/
$('.list-box-2 ul li').click(function(){

	$(this).css("color","red").siblings().css("color","black");
	$('.list-box-3 ul li').css("color","black");
	$('.list-box-4 ul li').css("color","black");
	$('.list-box-5 ul li').css("color","black");
	$('.list-box-6 ul li').css("color","black");

})
/*$('.list-box-3 ul li:eq(0)').click(function(){

	$(this).css("color","white").siblings().css("color","#a7c8db");
	$('.list-box-2 ul li').css("color","#a7c8db");
	$('.list-box-4 ul li').css("color","#a7c8db");
	$('.list-box-5 ul li').css("color","#a7c8db");
	$('.list-box-6 ul li').css("color","#a7c8db");
})
$('.list-box-4 ul li:eq(0)').click(function(){

	$(this).css("color","white").siblings().css("color","#a7c8db");
	$('.list-box-3 ul li').css("color","#a7c8db");
	$('.list-box-2 ul li').css("color","#a7c8db");
	$('.list-box-5 ul li').css("color","#a7c8db");
	$('.list-box-6 ul li').css("color","#a7c8db");
})
$('.list-box-4 ul li:eq(1)').click(function(){

	$(this).css("color","white").siblings().css("color","#a7c8db");
	$('.list-box-3 ul li').css("color","#a7c8db");
	$('.list-box-2 ul li').css("color","#a7c8db");
	$('.list-box-5 ul li').css("color","#a7c8db");
	$('.list-box-6 ul li').css("color","#a7c8db");
})
$('.list-box-5 ul li:eq(0)').click(function(){

	$(this).css("color","white").siblings().css("color","#a7c8db");
	$('.list-box-3 ul li').css("color","#a7c8db");
	$('.list-box-2 ul li').css("color","#a7c8db");
	$('.list-box-4 ul li').css("color","#a7c8db");
	$('.list-box-6 ul li').css("color","#a7c8db");
})
$('.list-box-6 ul li:eq(0)').click(function(){

	$(this).css("color","white").siblings().css("color","#a7c8db");
	$('.list-box-3 ul li').css("color","#a7c8db");
	$('.list-box-2 ul li').css("color","#a7c8db");
	$('.list-box-5 ul li').css("color","#a7c8db");
	$('.list-box-4 ul li').css("color","#a7c8db");
})
$('.list-box-6 ul li:eq(1)').click(function(){

	$(this).css("color","white").siblings().css("color","#a7c8db");
	$('.list-box-3 ul li').css("color","#a7c8db");
	$('.list-box-2 ul li').css("color","#a7c8db");
	$('.list-box-5 ul li').css("color","#a7c8db");
	$('.list-box-4 ul li').css("color","#a7c8db");
})
$('.list-box-6 ul li:eq(2)').click(function(){

	$(this).css("color","white").siblings().css("color","#a7c8db");
	$('.list-box-3 ul li').css("color","#a7c8db");
	$('.list-box-2 ul li').css("color","#a7c8db");
	$('.list-box-5 ul li').css("color","#a7c8db");
	$('.list-box-4 ul li').css("color","#a7c8db");
})*/





// iframe

/*$('.list-box-2 ul li:eq(0)').click(function(){
	$('#iframe-content').attr('src','iframe2.html');
})*/


$('.list-box-2 ul li').click(function(){
	var url=$(this).attr("url");
	$('#iframe-content').attr('src',url);
})
    $('.list-box-1').click(function(){
        var url=$(this).attr("url");
        $('#iframe-content').attr('src',url);
    })























})