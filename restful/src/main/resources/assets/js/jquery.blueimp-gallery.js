!function(e){"use strict";"function"==typeof define&&define.amd?define(["jquery","./blueimp-gallery"],e):e(window.jQuery,window.blueimp.Gallery)}(function(e,n){"use strict";e(document).on("click","[data-gallery]",function(t){var i=e(this).data("gallery"),o=e(i),r=o.length&&o||e(n.prototype.options.container),l={onopen:function(){r.data("gallery",this).trigger("open")},onopened:function(){r.trigger("opened")},onslide:function(){r.trigger("slide",arguments)},onslideend:function(){r.trigger("slideend",arguments)},onslidecomplete:function(){r.trigger("slidecomplete",arguments)},onclose:function(){r.trigger("close")},onclosed:function(){r.trigger("closed").removeData("gallery")}},a=e.extend(r.data(),{container:r[0],index:this,event:t},l),d=e('[data-gallery="'+i+'"]');return a.filter&&(d=d.filter(a.filter)),new n(d,a)})});