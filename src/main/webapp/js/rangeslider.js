(function(){function b(c){var d=this;d.rangeslider=new a(d,c);function e(h){var g=d.rangeslider;for(var f in g.components){g.components[f].init_()}if(g.options.hidden){g.hide()}if(g.options.locked){g.lock()}if(g.options.panel==false){g.hidePanel()}if(g.options.controlTime==false){g.hidecontrolTime()}g._reset();d.trigger("loadedRangeSlider")}if(d.techName=="Youtube"){d.one("error",function(f){switch(d.error){case 2:alert("The request contains an invalid parameter value. For example, this error occurs if you specify a video ID that does not have 11 characters, or if the video ID contains invalid characters, such as exclamation points or asterisks.");case 5:alert("The requested content cannot be played in an HTML5 player or another error related to the HTML5 player has occurred.");case 100:alert("The video requested was not found. This error occurs when a video has been removed (for any reason) or has been marked as private.");break;case 101:alert("The owner of the requested video does not allow it to be played in embedded players.");break;case 150:alert("The owner of the requested video does not allow it to be played in embedded players.");break;default:alert("Unknown Error");break}});d.on("firstplay",e)}else{d.one("playing",e)}console.log("Loaded Plugin RangeSlider")}videojs.plugin("rangeslider",b);function a(d,c){var d=d||this;this.player=d;this.components={};c=c||{};if(!c.hasOwnProperty("locked")){c.locked=false}if(!c.hasOwnProperty("hidden")){c.hidden=true}if(!c.hasOwnProperty("panel")){c.panel=true}if(!c.hasOwnProperty("controlTime")){c.controlTime=true}this.options=c;this.init()}a.prototype={init:function(){var c=this.player||{};this.updatePrecision=3;this.start=0;this.end=0;var e=c.controlBar;var d=e.progressControl.seekBar;this.components.RSTimeBar=d.RSTimeBar;this.components.ControlTimePanel=e.ControlTimePanel;this.rstb=this.components.RSTimeBar;this.box=this.components.SeekRSBar=this.rstb.SeekRSBar;this.bar=this.components.SelectionBar=this.box.SelectionBar;this.left=this.components.SelectionBarLeft=this.box.SelectionBarLeft;this.right=this.components.SelectionBarRight=this.box.SelectionBarRight;this.tp=this.components.TimePanel=this.box.TimePanel;this.tpl=this.components.TimePanelLeft=this.tp.TimePanelLeft;this.tpr=this.components.TimePanelRight=this.tp.TimePanelRight;this.ctp=this.components.ControlTimePanel;this.ctpl=this.components.ControlTimePanelLeft=this.ctp.ControlTimePanelLeft;this.ctpr=this.components.ControlTimePanelRight=this.ctp.ControlTimePanelRight},lock:function(){this.options.locked=true;this.ctp.enable(false);if(typeof this.box!="undefined"){videojs.addClass(this.box.el_,"locked")}},unlock:function(){this.options.locked=false;this.ctp.enable();if(typeof this.box!="undefined"){videojs.removeClass(this.box.el_,"locked")}},show:function(){this.options.hidden=false;if(typeof this.rstb!="undefined"){this.rstb.show();if(this.options.controlTime){this.showcontrolTime()}}},hide:function(){this.options.hidden=true;if(typeof this.rstb!="undefined"){this.rstb.hide();this.ctp.hide()}},showPanel:function(){this.options.panel=true;if(typeof this.tp!="undefined"){videojs.removeClass(this.tp.el_,"disable")}},hidePanel:function(){this.options.panel=false;if(typeof this.tp!="undefined"){videojs.addClass(this.tp.el_,"disable")}},showcontrolTime:function(){this.options.controlTime=true;if(typeof this.ctp!="undefined"){this.ctp.show()}},hidecontrolTime:function(){this.options.controlTime=false;if(typeof this.ctp!="undefined"){this.ctp.hide()}},setValue:function(c,h,f){var f=typeof f!="undefined"?f:true;var e=this._percent(h);var d=(c===0||c===1);var g=!this.locked;if(g&&d){this.box.setPosition(c,e,f)}},setValues:function(e,c,d){var d=typeof d!="undefined"?d:true;this._reset();this._setValuesLocked(e,c,d)},getValues:function(){var d={},e,c;e=this.start||this._getArrowValue(0);c=this.end||this._getArrowValue(1);return{start:e,end:c}},playBetween:function(e,c,d){d=typeof d=="undefined"?true:d;this.player.currentTime(e);this.player.play();if(d){this.show();this._reset()}else{this.hide()}this._setValuesLocked(e,c);this.bar.activatePlay(e,c)},loop:function(f,d,c){var e=this.player;if(e){e.on("pause",videojs.bind(this,function(){this.looping=false}));c=typeof c==="undefined"?true:c;if(c){this.show();this._reset()}else{this.hide()}this._setValuesLocked(f,d);this.timeStart=f;this.timeEnd=d;this.looping=true;this.player.currentTime(f);this.player.play();this.player.on("timeupdate",videojs.bind(this,this.bar.process_loop))}},_getArrowValue:function(d){var d=d||0;var e=this.player.duration();e=typeof e=="undefined"?0:e;var c=this[d===0?"left":"right"].el_.style.left.replace("%","");if(c==""){c=d===0?0:100}return videojs.round(this._seconds(c/100),this.updatePrecision-1)},_percent:function(d){var c=this.player.duration();if(isNaN(c)){return 0}return Math.min(1,Math.max(0,d/c))},_seconds:function(c){var d=this.player.duration();if(isNaN(d)){return 0}return Math.min(d,Math.max(0,c*d))},_reset:function(){var c=this.player.duration();this.tpl.el_.style.left="0%";this.tpr.el_.style.left="100%";this._setValuesLocked(0,c)},_setValuesLocked:function(f,c,e){var d=typeof e!="undefined";var e=typeof e!="undefined"?e:true;if(this.options.locked){this.unlock();this.setValue(0,f,e);this.setValue(1,c,e);this.lock()}else{this.setValue(0,f,e);this.setValue(1,c,e)}if(d){this._triggerSliderChange()}},_checkControlTime:function(j,f,x){var t=f[0],p=f[1],o=f[2],q=t.value,n=p.value,r=o.value,l,v,i;j=j||0;if(q!=x[0]){l=t;v=q;i=x[0]}else{if(n!=x[1]){l=p;v=n;i=x[1]}else{if(r!=x[2]){l=o;v=r;i=x[2]}else{return false}}}var c=this.player.duration()||0,g;var d=/^\d+$/;if(!d.test(v)||v>60){v=v==""?"":i}q=q==""?0:q;n=n==""?0:n;r=r==""?0:r;g=videojs.TextTrack.prototype.parseCueTime(q+":"+n+":"+r);if(g>c){l.value=i;l.style.border="1px solid red"}else{l.value=v;t.style.border=p.style.border=o.style.border="1px solid transparent";this.setValue(j,g,false);this._triggerSliderChange()}if(j===1){var u=this.ctpl.el_.children,e=videojs.TextTrack.prototype.parseCueTime(u[0].value+":"+u[1].value+":"+u[2].value);if(g<e){l.style.border="1px solid red"}}else{var k=this.ctpr.el_.children,w=videojs.TextTrack.prototype.parseCueTime(k[0].value+":"+k[1].value+":"+k[2].value);if(g>w){l.style.border="1px solid red"}}},_triggerSliderChange:function(){this.player.trigger("sliderchange")}};videojs.Player.prototype.lockSlider=function(){return this.rangeslider.lock()};videojs.Player.prototype.unlockSlider=function(){return this.rangeslider.unlock()};videojs.Player.prototype.showSlider=function(){return this.rangeslider.show()};videojs.Player.prototype.hideSlider=function(){return this.rangeslider.hide()};videojs.Player.prototype.showSliderPanel=function(){return this.rangeslider.showPanel()};videojs.Player.prototype.hideSliderPanel=function(){return this.rangeslider.hidePanel()};videojs.Player.prototype.showControlTime=function(){return this.rangeslider.showcontrolTime()};videojs.Player.prototype.hideControlTime=function(){return this.rangeslider.hidecontrolTime()};videojs.Player.prototype.setValueSlider=function(d,c){return this.rangeslider.setValues(d,c)};videojs.Player.prototype.playBetween=function(d,c){return this.rangeslider.playBetween(d,c)};videojs.Player.prototype.loopBetween=function(d,c){return this.rangeslider.loop(d,c)};videojs.Player.prototype.getValueSlider=function(){return this.rangeslider.getValues()};videojs.SeekBar.prototype.options_.children.RSTimeBar={};videojs.ControlBar.prototype.options_.children.ControlTimePanel={};videojs.RSTimeBar=videojs.Component.extend({init:function(d,c){videojs.Component.call(this,d,c)}});videojs.RSTimeBar.prototype.init_=function(){this.rs=this.player_.rangeslider};videojs.RSTimeBar.prototype.options_={children:{SeekRSBar:{}}};videojs.RSTimeBar.prototype.createEl=function(){return videojs.Component.prototype.createEl.call(this,"div",{className:"vjs-timebar-RS",innerHTML:""})};videojs.SeekRSBar=videojs.Component.extend({init:function(d,c){videojs.Component.call(this,d,c);this.on("mousedown",this.onMouseDown)}});videojs.SeekRSBar.prototype.init_=function(){this.rs=this.player_.rangeslider};videojs.SeekRSBar.prototype.options_={children:{SelectionBar:{},SelectionBarLeft:{},SelectionBarRight:{},TimePanel:{},}};videojs.SeekRSBar.prototype.createEl=function(){return videojs.Component.prototype.createEl.call(this,"div",{className:"vjs-rangeslider-holder"})};videojs.SeekRSBar.prototype.onMouseDown=function(c){c.preventDefault();videojs.blockTextSelection();if(!this.rs.options.locked){videojs.on(document,"mousemove",videojs.bind(this,this.onMouseMove));videojs.on(document,"mouseup",videojs.bind(this,this.onMouseUp))}};videojs.SeekRSBar.prototype.onMouseUp=function(c){videojs.off(document,"mousemove",this.onMouseMove,false);videojs.off(document,"mouseup",this.onMouseUp,false)};videojs.SeekRSBar.prototype.onMouseMove=function(d){var e=this.calculateDistance(d);if(this.rs.left.pressed){this.setPosition(0,e)}else{if(this.rs.right.pressed){this.setPosition(1,e)}}var c=this.player_.controlBar.currentTimeDisplay;c.contentEl_.innerHTML='<span class="vjs-control-text">' + c.localize('Current Time') + '</span>'+vjs.formatTime(this.rs._seconds(e),this.player_.duration());if(this.rs.left.pressed||this.rs.right.pressed){this.rs._triggerSliderChange()}};videojs.SeekRSBar.prototype.setPosition=function(k,f,e){var e=typeof e!="undefined"?e:true;var k=k||0;if(this.rs.options.locked){return false}if(isNaN(f)){return false}if(!(k===0||k===1)){return false}var d=this.rs.left.el_,g=this.rs.right.el_,u=this.rs[k===0?"left":"right"].el_,v=this.rs.tpr.el_,x=this.rs.tpl.el_,t=this.rs.bar,w=this.rs[k===0?"ctpl":"ctpr"].el_;if((k===0?t.updateLeft(f):t.updateRight(f))){u.style.left=(f*100)+"%";k===0?t.updateLeft(f):t.updateRight(f);this.rs[k===0?"start":"end"]=this.rs._seconds(f);if(k===0){if((f)>=0.9){d.style.zIndex=25}else{d.style.zIndex=10}}var p=videojs.formatTime(this.rs._seconds(f)),c=x.children[0].innerHTML.length;var i,j,o;if(c<=4){o=this.player_.isFullScreen?3.25:6.5}else{if(c<=5){o=this.player_.isFullScreen?4:8}else{o=this.player_.isFullScreen?5:10}}if(p.length<=4){i=this.player_.isFullScreen?97:93;j=this.player_.isFullScreen?0.1:0.5}else{if(p.length<=5){i=this.player_.isFullScreen?96:92;j=this.player_.isFullScreen?0.1:0.5}else{i=this.player_.isFullScreen?95:91;j=this.player_.isFullScreen?0.1:0.5}}if(k===0){x.style.left=Math.max(j,Math.min(i,(f*100-o/2)))+"%";if((v.style.left.replace("%","")-x.style.left.replace("%",""))<=o){x.style.left=Math.max(j,Math.min(i,v.style.left.replace("%","")-o))+"%"}x.children[0].innerHTML=p}else{v.style.left=Math.max(j,Math.min(i,(f*100-o/2)))+"%";if(((v.style.left.replace("%","")||100)-x.style.left.replace("%",""))<=o){v.style.left=Math.max(j,Math.min(i,x.style.left.replace("%","")-0+o))+"%"}v.children[0].innerHTML=p}if(e){var l=p.split(":"),r,q,n;if(l.length==2){r=0;q=l[0];n=l[1]}else{r=l[0];q=l[1];n=l[2]}w.children[0].value=r;w.children[1].value=q;w.children[2].value=n}}return true};videojs.SeekRSBar.prototype.calculateDistance=function(e){var c=this.getRSTBX();var d=this.getRSTBWidth();var f=this.getWidth();c=c+(f/2);d=d-f;return Math.max(0,Math.min(1,(e.pageX-c)/d))};videojs.SeekRSBar.prototype.getRSTBWidth=function(){return this.el_.offsetWidth};videojs.SeekRSBar.prototype.getRSTBX=function(){return videojs.findPosition(this.el_).left};videojs.SeekRSBar.prototype.getWidth=function(){return this.rs.left.el_.offsetWidth};videojs.SelectionBar=videojs.Component.extend({init:function(d,c){videojs.Component.call(this,d,c);this.on("mouseup",this.onMouseUp);this.fired=false}});videojs.SelectionBar.prototype.init_=function(){this.rs=this.player_.rangeslider};videojs.SelectionBar.prototype.createEl=function(){return videojs.Component.prototype.createEl.call(this,"div",{className:"vjs-selectionbar-RS"})};videojs.SelectionBar.prototype.onMouseUp=function(){var h=this.rs.left.el_.style.left.replace("%",""),d=this.rs.right.el_.style.left.replace("%",""),g=this.player_.duration(),c=this.rs.updatePrecision,e=videojs.round(h*g/100,c),f=videojs.round(d*g/100,c);this.player_.currentTime(e);this.player_.play();this.rs.bar.activatePlay(e,f)};videojs.SelectionBar.prototype.updateLeft=function(f){var e=this.rs.right.el_.style.left!=""?this.rs.right.el_.style.left:100;var c=parseFloat(e)/100;var d=videojs.round((c-f),this.rs.updatePrecision);if(f<=(c+0.00001)){this.rs.bar.el_.style.left=(f*100)+"%";this.rs.bar.el_.style.width=(d*100)+"%";return true}return false};videojs.SelectionBar.prototype.updateRight=function(d){var c=this.rs.left.el_.style.left!=""?this.rs.left.el_.style.left:0;var f=parseFloat(c)/100;var e=videojs.round((d-f),this.rs.updatePrecision);if((d+0.00001)>=f){this.rs.bar.el_.style.width=(e*100)+"%";this.rs.bar.el_.style.left=((d-e)*100)+"%";return true}return false};videojs.SelectionBar.prototype.activatePlay=function(d,c){this.timeStart=d;this.timeEnd=c;this.suspendPlay();this.player_.on("timeupdate",videojs.bind(this,this._processPlay))};videojs.SelectionBar.prototype.suspendPlay=function(){this.fired=false;this.player_.off("timeupdate",videojs.bind(this,this._processPlay))};videojs.SelectionBar.prototype._processPlay=function(){if(this.player_.currentTime()>=this.timeStart&&(this.timeEnd<0||this.player_.currentTime()<this.timeEnd)){if(this.fired){return}this.fired=true}else{if(!this.fired){return}this.fired=false;this.player_.pause();this.player_.currentTime(this.timeEnd);this.suspendPlay()}};videojs.SelectionBar.prototype.process_loop=function(){var c=this.player;if(c&&this.looping){var d=c.currentTime();if(d<this.timeStart||this.timeEnd>0&&this.timeEnd<d){c.currentTime(this.timeStart)}}};videojs.SelectionBarLeft=videojs.Component.extend({init:function(d,c){videojs.Component.call(this,d,c);this.on("mousedown",this.onMouseDown);this.pressed=false}});videojs.SelectionBarLeft.prototype.init_=function(){this.rs=this.player_.rangeslider};videojs.SelectionBarLeft.prototype.createEl=function(){return videojs.Component.prototype.createEl.call(this,"div",{className:"vjs-rangeslider-handle vjs-selectionbar-left-RS",innerHTML:'<div class="vjs-selectionbar-arrow-RS"></div><div class="vjs-selectionbar-line-RS"></div>'})};videojs.SelectionBarLeft.prototype.onMouseDown=function(c){c.preventDefault();videojs.blockTextSelection();if(!this.rs.options.locked){this.pressed=true;videojs.on(document,"mouseup",videojs.bind(this,this.onMouseUp));videojs.addClass(this.el_,"active")}};videojs.SelectionBarLeft.prototype.onMouseUp=function(c){videojs.off(document,"mouseup",this.onMouseUp,false);videojs.removeClass(this.el_,"active");if(!this.rs.options.locked){this.pressed=false}};videojs.SelectionBarRight=videojs.Component.extend({init:function(d,c){videojs.Component.call(this,d,c);this.on("mousedown",this.onMouseDown);this.pressed=false}});videojs.SelectionBarRight.prototype.init_=function(){this.rs=this.player_.rangeslider};videojs.SelectionBarRight.prototype.createEl=function(){return videojs.Component.prototype.createEl.call(this,"div",{className:"vjs-rangeslider-handle vjs-selectionbar-right-RS",innerHTML:'<div class="vjs-selectionbar-arrow-RS"></div><div class="vjs-selectionbar-line-RS"></div>'})};videojs.SelectionBarRight.prototype.onMouseDown=function(c){c.preventDefault();videojs.blockTextSelection();if(!this.rs.options.locked){this.pressed=true;videojs.on(document,"mouseup",videojs.bind(this,this.onMouseUp));videojs.addClass(this.el_,"active")}};videojs.SelectionBarRight.prototype.onMouseUp=function(c){videojs.off(document,"mouseup",this.onMouseUp,false);videojs.removeClass(this.el_,"active");if(!this.rs.options.locked){this.pressed=false}};videojs.TimePanel=videojs.Component.extend({init:function(d,c){videojs.Component.call(this,d,c)}});videojs.TimePanel.prototype.init_=function(){this.rs=this.player_.rangeslider};videojs.TimePanel.prototype.options_={children:{TimePanelLeft:{},TimePanelRight:{},}};videojs.TimePanel.prototype.createEl=function(){return videojs.Component.prototype.createEl.call(this,"div",{className:"vjs-timepanel-RS"})};videojs.TimePanelLeft=videojs.Component.extend({init:function(d,c){videojs.Component.call(this,d,c)}});videojs.TimePanelLeft.prototype.init_=function(){this.rs=this.player_.rangeslider};videojs.TimePanelLeft.prototype.createEl=function(){return videojs.Component.prototype.createEl.call(this,"div",{className:"vjs-timepanel-left-RS",innerHTML:'<span class="vjs-time-text">00:00</span>'})};videojs.TimePanelRight=videojs.Component.extend({init:function(d,c){videojs.Component.call(this,d,c)}});videojs.TimePanelRight.prototype.init_=function(){this.rs=this.player_.rangeslider};videojs.TimePanelRight.prototype.createEl=function(){return videojs.Component.prototype.createEl.call(this,"div",{className:"vjs-timepanel-right-RS",innerHTML:'<span class="vjs-time-text">00:00</span>'})};videojs.ControlTimePanel=videojs.Component.extend({init:function(d,c){videojs.Component.call(this,d,c)}});videojs.ControlTimePanel.prototype.init_=function(){this.rs=this.player_.rangeslider};videojs.ControlTimePanel.prototype.options_={children:{ControlTimePanelLeft:{},ControlTimePanelRight:{},}};videojs.ControlTimePanel.prototype.createEl=function(){return videojs.Component.prototype.createEl.call(this,"div",{className:"vjs-controltimepanel-RS vjs-control",})};videojs.ControlTimePanel.prototype.enable=function(c){var c=typeof c!="undefined"?c:true;this.rs.ctpl.el_.children[0].disabled=c?"":"disabled";this.rs.ctpl.el_.children[1].disabled=c?"":"disabled";this.rs.ctpl.el_.children[2].disabled=c?"":"disabled";this.rs.ctpr.el_.children[0].disabled=c?"":"disabled";this.rs.ctpr.el_.children[1].disabled=c?"":"disabled";this.rs.ctpr.el_.children[2].disabled=c?"":"disabled"};videojs.ControlTimePanelLeft=videojs.Component.extend({init:function(d,c){videojs.Component.call(this,d,c);this.on("keyup",this.onKeyUp);this.on("keydown",this.onKeyDown)}});videojs.ControlTimePanelLeft.prototype.init_=function(){this.rs=this.player_.rangeslider;this.timeOld={}};videojs.ControlTimePanelLeft.prototype.createEl=function(){return videojs.Component.prototype.createEl.call(this,"div",{className:"vjs-controltimepanel-left-RS",innerHTML:'Start: <input type="text" id="controltimepanel" maxlength="2" value="00"/>:<input type="text" id="controltimepanel" maxlength="2" value="00"/>:<input type="text" id="controltimepanel" maxlength="2" value="00"/>'})};videojs.ControlTimePanelLeft.prototype.onKeyDown=function(c){this.timeOld[0]=this.el_.children[0].value;this.timeOld[1]=this.el_.children[1].value;this.timeOld[2]=this.el_.children[2].value};videojs.ControlTimePanelLeft.prototype.onKeyUp=function(c){this.rs._checkControlTime(0,this.el_.children,this.timeOld)};videojs.ControlTimePanelRight=videojs.Component.extend({init:function(d,c){videojs.Component.call(this,d,c);this.on("keyup",this.onKeyUp);this.on("keydown",this.onKeyDown)}});videojs.ControlTimePanelRight.prototype.init_=function(){this.rs=this.player_.rangeslider;this.timeOld={}};videojs.ControlTimePanelRight.prototype.createEl=function(){return videojs.Component.prototype.createEl.call(this,"div",{className:"vjs-controltimepanel-right-RS",innerHTML:'End: <input type="text" id="controltimepanel" maxlength="2" value="00"/>:<input type="text" id="controltimepanel" maxlength="2" value="00"/>:<input type="text" id="controltimepanel" maxlength="2" value="00"/>'})};videojs.ControlTimePanelRight.prototype.onKeyDown=function(c){this.timeOld[0]=this.el_.children[0].value;this.timeOld[1]=this.el_.children[1].value;this.timeOld[2]=this.el_.children[2].value};videojs.ControlTimePanelRight.prototype.onKeyUp=function(c){this.rs._checkControlTime(1,this.el_.children,this.timeOld)}})();