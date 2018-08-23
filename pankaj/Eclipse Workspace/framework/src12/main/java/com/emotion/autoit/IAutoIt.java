package com.emotion.autoit;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 12 NOV 2014
 */

import com.emotion.autoit.AutoItX.LPPOINT;

public interface IAutoIt {
    
    
    public void init();
    
    public int error();
    
    public int autoItSetOption(String option, int value);
    
    public void blockInput(int flag);
    
    public void cdTray(String drive, String action);
    
    public void clipGet(byte[] clip, int bufSize);
    
    public void clipPut(String clip);
    
    public void controlClick(String title, String text, String control, String button, int numClicks, int x, int y);
    public void controlClick(String title, String text, String control);
    
    public void controlCommand(String title, String text, String control, String command, String extra, byte[] result, int bufSize);
    
    public void controlListView(String title, String text, String control, String command, String extra1, String extra2, byte[] result, int bufSize);
    
    public int controlDisable(String title, String text, String control);
    
    public int controlEnable(String title, String text, String control);
    
    public int controlFocus(String title, String text, String control);
    
    public void controlGetFocus(String title, String text, byte[] controlWithFocus, int bufSize);
    
    public void controlGetHandle(String title, String text, String control, byte[] retText, int bufSize);
    
    public int controlGetPosX(String title, String text, String control);
    
    public int controlGetPosY(String title, String text, String control);
    
    public int controlGetPosHeight(String title, String text, String control);
    
    public int controlGetPosWidth(String title, String text, String control);
    
    public void controlGetText(String title, String text, String control, byte[] controlText, int bufSize);
    public String controlGetText(String title, String text, String control);
    
    public int controlHide(String title, String text, String control);
    
    public int controlMove(String title, String text, String control, int x, int y, int width, int height);
    
    public int controlSend(String title, String text, String control, String sendText, int mode);
    public int controlSend(String title, String text, String control, String sendText);
    
    public int controlSetText(String title, String text, String control, String controlText);
    
    public int controlShow(String title, String text, String control);
    
    public void driveMapAdd(String device, String szShare, int flags, String user, String pwd, byte[] result, int bufSize);
    
    public int driveMapDel(String device);
    
    public void driveMapGet(String device, byte[] mapping, int bufSize);
    
    public int iniDelete(String filename, String section, String key);
    
    public void iniRead(String filename, String section, String key, String defaultValue, byte[] value, int bufSize);
    
    public int iniWrite(String filename, String section, String key, String value);
    
    public int isAdmin();
    
    public int mouseClick(String button, int x, int y, int clicks, int speed);
    
    public int mouseClickDrag(String button, int x1, int y1, int x2, int y2, int speed);
    
    public void mouseDown(String button);
    
    public int mouseGetCursor();
    
    public int mouseGetPosX();
    
    public int mouseGetPosY();
    
    public int mouseMove(int x, int y, int speed);
    
    public void mouseUp(String button);
    
    public void mouseWheel(String direction, int clicks);
    
    public int opt(String option, int value);
    
    public int pixelChecksum(int left, int top, int right, int bottom,  int step);
    
    public int pixelGetColor(int x, int y);
    
    public void pixelSearch(int left, int top, int right, int bottom, int col,  int var,  int step, LPPOINT pointResult);
    
    public int processClose(String process);
    
    public int processExists(String process);
    
    public int processSetPriority(String process, int priority);
    
    public int processWait(String process, int timeout);
    
    public int processWaitClose(String process, int timeout);
    
    public int regDeleteKey(String keyname);
    
    public int regDeleteVal(String keyname, String valueName);
    
    public void regEnumKey(String keyname, int instance, byte[] result, int bufSize);
    
    public void regEnumVal(String keyname, int instance, byte[] result, int bufSize);
    
    public void regRead(String keyname, String valuename, byte[] retText, int bufSize);
    
    public int regWrite(String keyname, String valuename, String type, String value);
    
    public int run(String run, String dir, int showFlags);
    public int run(String run);
    
    public int runAsSet(String user, String domain, String password, int options);
    
    public int runWait(String run, String dir, int showFlags);
    
    public void send(String szSendText, int mode);
    
    public int shutdown(int flags);
    
    public void sleep(int milliseconds);
    
    public void statusbarGetText(String title, String text, int part, byte[] statusText, int bufSize);
    
    public void toolTip(String tip, int x, int y);
    
    public int winActive(String title, String text);
    
    public void winActivate(String title, String text);
    
    public int winClose(String title, String text);
    
    public int winExists(String title, String text);
    
    public int winGetCaretPosX();
    
    public int winGetCaretPosY();
    
    public void winGetClassList(String title, String text, byte[] retText, int bufSize);
    
    public int winGetClientSizeHeight(String title, String text);
    
    public int winGetClientSizeWidth(String title, String text);
    
    public void winGetHandle(String title, String text, byte[] retText, int bufSize);
    
    public int winGetPos(String title, String text);
    
    public void winGetProcess(String title, String text, byte[] retText, int bufSize);
    
    public int winGetState(String title, String text);
    
    public void winGetText(String title, String text, byte[] retText, int bufSize);
    public String winGetText(String title, String text);
    
    public void winGetTitle(String title, String text, byte[] retText, int bufSize);
    public String winGetTitle(String title, String text);
    
    public int winKill(String title, String text);
    
    public int winMenuSelectItem(String title, String text, String item1, String item2, String item3, String item4, String item5, String item6, String item7, String item8);
    
    public void winMinimizeAll();
    
    public void winMinimizeAllUndo();
    
    public int winMove(String title, String text, int x, int y, int width, int height);
    
    public int winSetOnTop(String title, String text, int flag);
    
    public int winSetState(String title, String text, int flags);
    
    public int winSetTitle(String title, String text, String newTitle);
    
    public int winSetTrans(String title, String text, int trans);
    
    public int winWait(String title, String text, int timeout);
    
    public int winWaitActive(String title, String text, int timeout);
    
    public int winWaitClose(String title, String text, int timeout);
    
    public int winWaitNotActive(String title, String text, int timeout);

}
