/**
 * 读漫画详细页面
 */

//跳转到相应的话数
$('#gotoEpisode').change(function(){
	var cid = $(this).val();
	var href = GLOBAL.view+'/'+cid;
	location.href = href;
});
//图片延迟加载
$("img.lazy").lazyload({
	threshold : 50,
	effect:"fadeIn"
});

/**
 * 上一个图片
 */
function goUp()
{
	var pId;
	var parent = $('#manga-imgs');
	parent.find('.preP').click(function(){
		var top = $(document).scrollTop();
		if (top <= 0)
			return;
			
		var sHeight = $(window).height() - 100;
		var offset = top - sHeight;
		// console.log(offset);
		$('body,html').animate({scrollTop: offset + 'px'}, 500);
//		var index = $(this).index('.preP')+1;
//		index -= 1;
//		if(index<=0)
//		{
//			return ;
//		}
//		
//		var preId = $('#p'+index);
//		var topSize = preId.offset().top;
//		if(!$(window).is(':animated'))
//		{
//			$('html,body').animate({scrollTop:topSize},'slow');	
//		}
		return ;
	})
}

/**
 * 下一个图片 
 */
function goDown()
{
//	var pId;
	var parent = $('#manga-imgs');
//	var count  = parent.find('.nextP').length;
	parent.find('.nextP').click(function(){
		var top = $(document).scrollTop();
		var sHeight = $(window).height() - 100;
		var offset = top + sHeight;
		$('body').stop();
		$('body,html').animate({scrollTop: offset + 'px'}, 500);

//		var index = $(this).index('.nextP')+1;
//		index += 1;
//		if(index >= (count+1))
//		{
//			return ;
//		}
//		
//		var preId = $('#p'+index);
//		var topSize = preId.offset().top;
//		if(!$(window).is(':animated'))
//		{
//			$('html,body').animate({scrollTop:topSize},'slow');	
//		}
		return ;
	})
}

goUp();
goDown();

//迟延计算
var lazyScroll = (function(pcount)
{
	var flag = true;
	var pcount = pcount;
	var allPageTopSizeArr = {};
	var currentTopSize = 0;
	
	/**
	 * 计算整个页面中所有单页面
	 * 距离顶部的值
	 */
	var allPageTopSize = function()
	{
		for(var i=1;i<=pcount;i++)
		{
			allPageTopSizeArr[i] = $('#p'+i).offset().top;
		}
		return allPageTopSizeArr;
	}
	
	/**
	 * 计算是停留在哪页
	 */
	var stopPageN = function(size)
	{
		for(var x in allPageTopSizeArr)
		{
			var cPage = allPageTopSizeArr[x];
			if(size>=cPage && size<=cPage+GLOBAL.imgHeight)
			{
				$.cookie('buka'+mid,cid+'/#p'+x,{ expires: 999, path: '/' });
				currentTopSize = $('#p'+x).offset().top;
				return ;
			}
		}
	}
	
	
	/**
	 * 记录浏览记录
	 */
	var run = function()
	{
		//当前滚动条的位置
		var currentScrollTop = $(this).scrollTop();
//		fixBar();
		stopPageN(currentScrollTop);
		flag=true;
	}
	
	/**
	 * 固定导航bar
	 * @param {Object} size
	 */
//	var fixBar = function()
//	{
//		var headNav =  $('#head-nav');
//		var headNavScrollTop = headNav.scroll().height();
//		$(window).scroll(function(){
//			var sTop = $(window).scrollTop();
//			if(sTop>=headNavScrollTop)
//			{
//				headNav.css('top',0);
//			}
//			else
//			{
//				headNav.css('top',58);
//			}
//			
//		})
//	}
	
	
	var init = function()
	{
		var allPageTopSizeArr = allPageTopSize();
//		fixBar();
		stopPageN(50);
		$(window).scroll(function(){
			if(flag)
			{
				var that = $(this);
				setTimeout(run.bind(that),200);
				flag=false;
			}
		})
	}
	return {
		init:init
	}
})(pcount);

//禁用右键菜单
function stopRightClick()
{
	if (window.Event) 
    	document.captureEvents(Event.MOUSEUP);
    function nocontextmenu(){ 
     event.cancelBubble = true 
     event.returnValue = false; 
     return false; 
    } 
    function norightclick(e){ 
     if (window.Event){ 
      if (e.which == 2 || e.which == 3) 
      return false; 
     } 
     else 
      if (event.button == 2 || event.button == 3){ 
       event.cancelBubble = true 
       event.returnValue = false; 
       return false; 
      } 
    } 
    document.oncontextmenu = nocontextmenu; // for IE5+ 
    document.onmousedown = norightclick; // for all others 
}

lazyScroll.init();

// stopRightClick();


