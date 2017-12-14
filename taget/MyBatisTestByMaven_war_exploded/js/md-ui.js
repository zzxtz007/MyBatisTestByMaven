function MdUi() {
    'use strict';

    /**
     * 根据User Agent判断是否为移动设备
     *
     * @returns {boolean} 移动设备返回true，否则返回false
     */
    var isMobile = function () {
        var ua = navigator.userAgent;
        return ua.match(/(windows phone|symbianos|android|mobile|playbook|ipad|iphone|ipod)/i) !==
            null;
    };

    /**
     * 获取鼠标位置相对容器的坐标
     */
    var getMousePos = function (e) {
        var elem = $(e.currentTarget);

        // 目标元素左上角距文档左上角的距离
        var left = elem.offset().left;
        var top = elem.offset().top;

        // 鼠标位置相对文档的坐标
        var mouseX = e.pageX || e.changedTouches[0].pageX;
        var mouseY = e.pageY || e.changedTouches[0].pageY;

        // 鼠标位置相对容器的坐标
        var x = Math.round(mouseX - left);
        var y = Math.round(mouseY - top);

        return {
            'x': x,
            'y': y
        };
    };

    /**
     * 计算两点距离
     *
     * @param x1 第一点X轴坐标
     * @param y1 第一点Y轴坐标
     * @param x2 第二点X轴坐标
     * @param y2 第二点Y轴坐标
     */
    var pointDist = function (x1, y1, x2, y2) {
        var xDis = Math.abs(x1 - x2);
        var yDis = Math.abs(y1 - y2);
        return Math.sqrt(Math.pow(xDis, 2) + Math.pow(yDis, 2));
    };

    /**
     * Material-Design 按钮水波纹
     *
     * @param elem 欲在其上释放水波纹的元素
     * @param isFromCenter 是否从中心释放水波纹，如果为false则从鼠标点击位置释放
     * @param e 事件对象
     */
    var MdRipple = function (elem, isFromCenter, e) {
        elem = $(elem);

        // 如果从中心释放，则获取元素中心坐标，否则获取鼠标坐标
        var centerPoint;
        var width = parseInt(elem[0].offsetWidth);
        var height = parseInt(elem[0].offsetHeight);
        if (isFromCenter) {
            centerPoint = {
                'x': width / 2,
                'y': height / 2
            };
        } else {
            centerPoint = getMousePos(e);
        }

        // 计算鼠标位置与距容器四角最远的一个角之间的距离，用作ripple的半径
        var mouseToCornerDist = [
            Math.round(pointDist(centerPoint.x, centerPoint.y, 0, 0)),
            Math.round(pointDist(centerPoint.x, centerPoint.y, width, 0)),
            Math.round(pointDist(centerPoint.x, centerPoint.y, width, height)),
            Math.round(pointDist(centerPoint.x, centerPoint.y, 0, height))
        ];
        var radius = Math.max.apply(Math, mouseToCornerDist);

        // 构建并插入ripple div
        var div = $(document.createElement('div'));
        div.addClass('md_ripple');
        div.css('top', centerPoint.y + 'px');
        div.css('left', centerPoint.x + 'px');
        div.css('height', radius * 2 + 'px');
        div.css('width', radius * 2 + 'px');
        div.css('margin-left', -radius + 'px');
        div.css('margin-top', -radius + 'px');
        elem.append(div);

        // 延迟使transition生效
        setTimeout(function () {
            div.css('transform', 'scale(1)');
        }, 1);

        // 鼠标抬起，渐隐、移除ripple
        $(document).one(isMobile() ? 'touchend' : 'mouseup', function () {
            div.css('opacity', 0);
            div.on('transitionend', function (e) {
                e = e || window.event;
                var evtName = e.originalEvent.propertyName;
                if (evtName === 'opacity') {
                    div.remove();
                }
            });
        });
    };

    /**
     * 为元素绑定水波纹特效
     * 元素css应设为 position: relative; overflow:hidden
     *
     * @param elem 欲在其上绑定水波纹特效的元素
     * @param isFromCenter 是否从中心释放水波纹，如果为false则从鼠标点击位置释放
     */
    var bindRipple = function (elem, isFromCenter) {
        elem.on(isMobile() ? 'touchstart' : 'mousedown', function (e) {
            e = e || window.event;

            MdRipple(elem, isFromCenter, e);
        });
    };

    /**
     * 初始化按钮
     *
     * @param btn 按钮对象
     */
    var initButton = function (btn) {
        var isFromCenter = btn.hasClass('center-ripple');
        bindRipple(btn, isFromCenter);
    };

    /**
     * 初始化input（text、password）
     *
     * @param input 输入框元素
     */
    var initInputText = function (input) {
        var inputId = input.attr('id');
        var title = input.attr('title');
        var isDisabled = input.attr('disabled') !== undefined;

        // 创建组件
        var box = $(document.createElement('div'));
        var frag = $(document.createDocumentFragment());
        var label = $(document.createElement('label'));
        var barBack = $(document.createElement('div'));
        var barFront = $(document.createElement('div'));

        // 设置组件属性
        box.addClass('md-input-box');
        if (isDisabled) {
            box.addClass('disabled');
        }
        label.attr('for', inputId);
        label.html(title);
        barBack.addClass('bar back');
        barFront.addClass('bar front');

        // 绑定事件
        input.on('focus', function () {
            label.addClass('focus contract');
            barFront.addClass('expand');
        });
        input.on('blur', function () {
            barFront.removeClass('expand');
            label.removeClass('focus');
            if (input.val().length === 0) {
                label.removeClass('contract');
            }
        });

        // 插入组件
        input.after(box);
        frag.append(label);
        frag.append(input);
        frag.append(barBack);
        frag.append(barFront);
        box.append(frag);
    };

    /**
     * 初始化input（checkbox）
     *
     * @param checkbox 多选框元素
     */
    var initInputCheckbox = function (checkbox) {
        var id = checkbox.attr('id');
        var title = checkbox.attr('title');
        var isDisabled = checkbox.attr('disabled') !== undefined;

        // 创建组件
        var frag = $(document.createDocumentFragment());
        var label = $(document.createElement('label'));
        var rippleBox = $(document.createElement('span'));
        var checkboxSvg = $(document.createElementNS('http://www.w3.org/2000/svg', 'svg'));
        var checkboxSvgPath = $(document.createElementNS('http://www.w3.org/2000/svg', 'path'));
        var checkSvg = $(document.createElementNS('http://www.w3.org/2000/svg', 'svg'));
        var checkSvgPath = $(document.createElementNS('http://www.w3.org/2000/svg', 'path'));

        var titleBox = $(document.createElement('span'));

        // 设置组件属性
        label.addClass('md-checkbox-box');
        if (isDisabled) {
            label.addClass('disabled');
        }
        label.attr('for', id);
        rippleBox.addClass('ripple_box');
        checkboxSvg.addClass('checkbox_svg');
        checkboxSvgPath.attr('d', 'm16,2l0,14l-14,0l0,-14l14,0m0,-2l-14,0c-1.1,0 -2,0.9 -2,2l0,14' +
            'c0,1.1 0.9,2 2,2l14,0c1.1,0 2,-0.9 2,-2l0,-14c0,-1.1 -0.9,-2 -2,-2z');
        checkSvg.addClass('check_svg');
        checkSvgPath.attr('d', 'm16,0l-14,0c-1.11,0 -2,0.9 -2,2l0,14c0,1.1 0.89,2 2,2l14,0c1.11,0' +
            ' 2,-0.9 2,-2l0,-14c0,-1.1 -0.89,-2 -2,-2zm-9,14l-5,-5l1.41,-1.41l3.59,3.58l7.59,-7.5' +
            '9l1.41,1.42l-9,9z');
        titleBox.addClass('title');
        titleBox.html(title);

        /**
         * 检测check状态，增删选中效果
         */
        function toggleCheck() {
            var isChecked = checkbox.is(':checked');
            if (isChecked) { // 选中
                checkSvg.addClass('check');
                checkboxSvg.addClass('check');
            } else { // 未选中
                checkSvg.removeClass('check');
                checkboxSvg.removeClass('check');
            }
        }

        // 初始状态
        toggleCheck();

        // 绑定事件
        bindRipple(rippleBox, true);
        checkbox.on('change', toggleCheck);

        // 插入组件
        checkbox.after(label);
        checkboxSvg.append(checkboxSvgPath);
        checkSvg.append(checkSvgPath);
        rippleBox.append(checkboxSvg);
        rippleBox.append(checkSvg);
        frag.append(checkbox);
        frag.append(rippleBox);
        frag.append(titleBox);
        label.append(frag);
    };

    /**
     * 初始化textarea
     *
     * @param textarea 文本域元素
     */
    var initTextarea = function (textarea) {
        var textareaId = textarea.attr('id');
        var title = textarea.attr('title');

        // 创建组件
        var box = $(document.createElement('div'));
        var frag = $(document.createDocumentFragment());
        var label = $(document.createElement('label'));
        var barBack = $(document.createElement('div'));
        var barFront = $(document.createElement('div'));

        // 设置组件属性
        box.addClass('md-input-box');
        label.attr('for', textareaId);
        label.html(title);
        barBack.addClass('bar back');
        barFront.addClass('bar front');

        // 绑定事件
        textarea.on('focus', function () {
            label.addClass('focus contract');
            barFront.addClass('expand');
        });
        textarea.on('blur', function () {
            barFront.removeClass('expand');
            label.removeClass('focus');
            if (textarea.val().length === 0) {
                label.removeClass('contract');
            }
        });
        textarea.on('input', function () {
            textarea.height('auto');
            textarea.height(textarea[0].scrollHeight);
        });

        // 插入组件
        textarea.after(box);
        frag.append(label);
        frag.append(textarea);
        frag.append(barBack);
        frag.append(barFront);
        box.append(frag);
    };

    /**
     * 初始化select（通过ul实现）
     *
     * @param ul 列表元素
     */
    var initSelect = function (ul) {
        // 创建组件
        var box = $(document.createElement('ul'));
        var boxLi = ul.children('li.selected').eq(0).clone(false);
        var boxTitle = $(document.createElement('h3'));
        var boxContent = $(document.createElement('span'));

        // 设置组件属性
        box.addClass('md-ui md-select md-select-box');
        boxLi.removeClass('selected');

        // 绑定事件
        ul.on('click', function (e) {
            e = e || window.event;

            ul.children('li.selected').removeClass('selected');
            $(e.target).addClass('selected');
        });
        bindRipple(boxLi, false);

        // 插入组件
        box.append(boxLi);
        ul.before(box);
    };

    /**
     * 初始化 Material Design 元素
     */
    this.init = function () {
        var mdElemArr = $('.md-ui');

        // 遍历所有MD-UI元素
        for (var i = 0; i < mdElemArr.length; i++) {
            var mdElem = mdElemArr.eq(i);

            // 跳过已初始化的元素
            if (mdElem.hasClass('md-done')) {
                continue;
            }

            // 获取标签名
            var tag = mdElem[0].tagName.toLowerCase();

            // 根据标签名init
            switch (tag) {
                case 'button': {
                    initButton(mdElem);
                    break;
                }
                case 'input': {
                    var inputType = mdElem.attr('type');

                    switch (inputType) {
                        case undefined:
                        case 'text':
                        case 'password': {
                            initInputText(mdElem);
                            break;
                        }
                        case 'checkbox': {
                            initInputCheckbox(mdElem);
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                    break;
                }
                case 'textarea': {
                    initTextarea(mdElem);
                    break;
                }
                case 'ul': {
                    if (mdElem.hasClass('md-select')) {
                        initSelect(mdElem);
                    }
                    break;
                }
                default: {
                    break;
                }
            }

            mdElem.addClass('md-done');
        }
    };
}
