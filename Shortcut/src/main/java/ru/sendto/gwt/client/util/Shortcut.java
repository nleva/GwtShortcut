/**
 * 
 */
package ru.sendto.gwt.client.util;

import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.ui.Widget;

/**
 * Gwt simple shortcut access
 * @author Lev Nadeinskiy created Feb 15, 2016
 */
public class Shortcut {

	private boolean onKeyDown;
	private boolean onKeyUp;
	private boolean onKeyPress;
	
	private boolean alt;
	private boolean shift;
	private boolean ctrl;
	private boolean meta;
	private int key;
	private int wheel;

	private Shortcut() {}
	
	public int getWheel() {
		return wheel;
	}

	public Shortcut setWheel(int wheel) {
		this.wheel = wheel;
		return this;
	}

	public boolean isAlt() {
		return alt;
	}

	public Shortcut setAlt(boolean alt) {
		this.alt = alt;
		return this;
	}
	public Shortcut withAlt() {
		this.alt = true;
		return this;
	}

	public boolean isShift() {
		return shift;
	}

	public Shortcut setShift(boolean shift) {
		this.shift = shift;
		return this;
	}
	public Shortcut withShift() {
		this.shift = true;
		return this;
	}

	public boolean isCtrl() {
		return ctrl;
	}

	public Shortcut setCtrl(boolean ctrl) {
		this.ctrl = ctrl;
		return this;
	}
	
	public Shortcut withCtrl() {
		this.ctrl = true;
		return this;
	}

	public boolean isMeta() {
		return meta;
	}

	public Shortcut setMeta(boolean meta) {
		this.meta = meta;
		return this;
	}
	public Shortcut withMeta() {
		this.meta = true;
		return this;
	}

	public int getKey() {
		return key;
	}

	public Shortcut setKey(int key) {
		this.key = key;
		return this;
	}

	public Shortcut setAction(Runnable r) {
		add(r, this);
		return this;
	}

	static public Shortcut bind(Runnable r){
		return new Shortcut().setAction(r);
	}
	static public Shortcut bind(Runnable r, Element t){
		Shortcut shortcut = new Shortcut();
		add(r, shortcut, t);
		return shortcut;
	}
	static public Shortcut bind(Runnable r, Widget w){
		return bind(r, w.getElement());
	}
	
	static public void add(Runnable r, Shortcut shct, Element...target) {
		
		if(target!=null&&target[0]!=null){
			
			target[0].setTabIndex(target[0].getTabIndex());
		}
			Event.addNativePreviewHandler(e -> {
				
				if(checkEvent(shct, e)){
					if(target!=null&&target[0]!=null){
						if(e.getNativeEvent().getEventTarget()!=target[0].cast()){
							return;
						}
					}
					e.getNativeEvent().preventDefault();
					
					r.run();
				}
	
			});
	}

	/**
	 * @param shct
	 * @param e
	 */
	private static boolean checkEvent(Shortcut shct, NativePreviewEvent e) {
		NativeEvent nativeEvent = e.getNativeEvent();
		return checkNativeEvent(shct, nativeEvent);
	}

	/**
	 * @param shct
	 * @param nativeEvent
	 */
	private static boolean checkNativeEvent(Shortcut shct, NativeEvent nativeEvent) {
		if ((nativeEvent.getAltKey() ^ shct.isAlt())
			||(nativeEvent.getShiftKey() ^ shct.isShift())
			||(nativeEvent.getCtrlKey() ^ shct.isCtrl())
			||(nativeEvent.getMetaKey() ^ shct.isMeta())
			||(nativeEvent.getMouseWheelVelocityY() > 0 ^ shct.getWheel() > 0)
			||(nativeEvent.getMouseWheelVelocityY() < 0 ^ shct.getWheel() < 0)
			||(nativeEvent.getMouseWheelVelocityY() == 0 ^ shct.getWheel() == 0)
			||(nativeEvent.getKeyCode() != shct.getKey())
			||((nativeEvent.getType().intern()==BrowserEvents.KEYDOWN ^ shct.isOnKeyDown()))
			||((nativeEvent.getType().intern()==BrowserEvents.KEYUP ^ shct.isOnKeyUp()))
			||((nativeEvent.getType().intern()==BrowserEvents.KEYPRESS ^ shct.isOnKeyPress()))){
			return false;
		}
		return true;
	}
	

	public boolean isOnKeyDown() {
		return onKeyDown;
	}

	public Shortcut setOnKeyDown(boolean onKeyDown) {
		this.onKeyDown = onKeyDown;
		return this;
	}
	
	public Shortcut setOnKeyDown() {
		this.onKeyDown = true;
		return this;
	}

	public boolean isOnKeyUp() {
		return onKeyUp;
	}

	public Shortcut setOnKeyUp(boolean onKeyUp) {
		this.onKeyUp = onKeyUp;
		return this;
	}
	
	public Shortcut setOnKeyUp() {
		this.onKeyUp = true;
		return this;
	}

	public boolean isOnKeyPress() {
		return onKeyPress;
	}

	public Shortcut setOnKeyPress(boolean onKeyPress) {
		this.onKeyPress = onKeyPress;
		return this;
	}
	
	public Shortcut setOnKeyPress() {
		this.onKeyPress = true;
		return this;
	}

}
