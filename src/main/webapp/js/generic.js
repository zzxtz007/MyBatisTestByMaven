/**
 * 消息栏超时定时器
 */
var msgTimeout;

/**
 * 弹出消息栏，显示指定消息内容
 *
 * @param content 消息内容，建议不超过22字（移动端最低可用宽度320px，仅能容纳22个14px的文字）
 * @param type 消息类型，接受 "success" 和 "err" 两种类型
 */
function showMsg(content, type) {
    'use strict';

    var msgBoxElem = $('#msg-box');

    // 如果消息栏已弹出，则隐藏消息栏，随后重新弹出
    if (!msgBoxElem.hasClass('msg-hide')) {
        msgBoxElem.addClass('msg-hide');
        msgBoxElem.one('transitionend', show);
    } else {
        show();
    }

    function show() {
        // 移除样式class
        msgBoxElem.removeClass('msg-success msg-err');

        // 添加新的样式class
        switch (type) {
            case 'success': {
                msgBoxElem.addClass('msg-success');
                break;
            }
            case 'err': {
                msgBoxElem.addClass('msg-err');
                break;
            }
            default: {
                return;
            }
        }

        // 设置消息内容
        msgBoxElem.html(content);

        msgBoxElem.removeClass('msg-hide');

        // 重置定时器
        clearTimeout(msgTimeout);

        // 2秒后消失
        msgTimeout = setTimeout(function () {
            msgBoxElem.addClass('msg-hide');
        }, 2000);
    }
}

$(function () {
    // 消息栏点击关闭
    $('#msg-box').on('click', function () {
        $(this).addClass('msg-hide');
    });
});
