function add(click,html,title){
    form.on(click,function (){
        layer.open(
            {
                type: 2, // 独立的一个 iframe 小页面
                content: html,//传入的值
                area: ['550px', '600px'],//大小
                titie: title,//标题
                end: function () {//弹框结束执行function
                    //弹框结束，加载表格数据
                    table.reload("infoTable");
                }
            }
        )
    })
}
/**
 * 搜索,表格的重载
 */
function search(click,url){
    form.on('submit('+click+')', function (data) {
        //表格的重载
        table.reload('infoTable',
            {
                url: url,
                method: 'post',
                where: data.field,
                page: false
            },
            'data'
        );
        return false;
    });
}

//获取浏览器的cookie
function getCookie(cookieName) {
    const strCookie = document.cookie
    const cookieList = strCookie.split(';')

    for(let i = 0; i < cookieList.length; i++) {
        const arr = cookieList[i].split('=')
        if (cookieName === arr[0].trim()) {
            return arr[1]
        }
    }

    return ''
}

function welcome(){
    //根据cookie在页面增加。。。欢迎您
    $(function(){
        var username = getCookie("username");
        $("#username").html(username+"，欢迎您");
    });
}
//头部事件
function head(){
    //头部事件
    util.event('lay-header-event', {
        //左侧菜单事件
        menuLeft: function (othis) {
            layer.msg('展开左侧菜单的操作', {icon: 0});
        }
        , menuRight: function () {
            layer.open({
                type: 1
                , content: '<div style="padding: 15px;">处理右侧面板的操作</div>'
                , area: ['260px', '100%']
                , offset: 'rt' //右上角
                , anim: 5
                , shadeClose: true
            });
        }
    });
}
//刷新保持在当前页
function flash(){
    window.onbeforeunload = function(){
        document.cookie="src="+$('iframe').attr('src');
    }

    window.onload = function()
    {
        if(document.cookie.match(/src=([^;]+)(;|$)/)!=null){
            var arr=document.cookie.match(/src=([^;]+)(;|$)/); //cookies中不为空，则读取iframe的src
            $('iframe').attr('src',arr[1]);
        }
    }
}

//退出登录，跳转到登录界面
function loginOut(){
    $.ajax({
        url : '/login/loginOut',
        success : function (){
            window.location.href = "/login.html";
        }
    })
}

/**
 * iframe跳转页面
 * @param ret 跳转页面的URL
 */
function iframeURL (ret){
    let iframe = document.getElementById("ifream-1");
    iframe.src=ret;
}

/**
 * 在页面显示时间
 */
function showTime(){
    setInterval("document.all.date.innerHTML=new Date().toLocaleString()",1000);
}
