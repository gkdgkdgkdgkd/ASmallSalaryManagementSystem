package com.test.log;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FormatterBuilder {
    private final StringBuilder str = new StringBuilder("\n");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MM dd-HH:mm:ss:SSS");
    private static final String titleChar = "********************";
    private static final int titleSpaceNums = 30;
    private static final String contentChar = "----------";
    private static final int contentSpaceNums = 50;
    private static final String contentSeparateChar = " : ";
    private static StackTraceElement [] traceElements;
    private static int position;

    public FormatterBuilder position()
    {
        setPosition();
        content("class",getOccurClass());
        content("method",getOccurMethod());
        content("line",getOccurLine()+"");
        return this;
    }

    public FormatterBuilder warn()
    {
        return level("warn");
    }

    public FormatterBuilder info()
    {
        return level("info");
    }

    public FormatterBuilder time()
    {
        content("time",getCurrentTime());
        return this;
    }

    private FormatterBuilder level(String level)
    {
        content("level",level);
        return this;
    }

    public FormatterBuilder cellphone(String cellphone)
    {
        content("cellphone",cellphone);
        return this;
    }

    public FormatterBuilder message(String message)
    {
        content("message",message);
        return this;
    }

    public FormatterBuilder worker(String worker)
    {
        content("worker",worker);
        return this;
    }

    public FormatterBuilder password(String password)
    {
        content("password",password);
        return this;
    }

    public FormatterBuilder code(String code)
    {
        content("code",code);
        return this;
    }

    public String build()
    {
        formatLine(true,"end");
        return str.toString();
    }

    public FormatterBuilder title(String titleStr)
    {
        formatLine(true,titleStr);
        return this;
    }

    private static String getCurrentTime()
    {
        return dateTimeFormatter.format(ZonedDateTime.now());
    }

    private static void setPosition()
    {
        traceElements = Thread.currentThread().getStackTrace();
        position = 5;
    }

    private static String getOccurClass()
    {
        return traceElements[position].getClassName();
    }

    private static String getOccurMethod()
    {
        return traceElements[position].getMethodName();
    }

    private static int getOccurLine()
    {
        return traceElements[position].getLineNumber();
    }

    //格式化内容,保证每行70个字符,长的切断分开记录
    public void content(String type, String value)
    {
        int len = type.length()+contentSeparateChar.length()+value.length();
        if(contentSpaceNums - len >=0)
            formatLine(false,type,contentSeparateChar,value);
        else
        {
            formatLine(false,type);
            len = value.length();
            for(int i=0;i<value.length();i+=40)
                formatLine(false,value.substring(i, Math.min(i + 40, len)));
        }
    }

    //格式化每一行,参数表示是否是标题,可变参数表示内容
    private void formatLine(boolean isTitle,String ...strs)
    {
        if (isTitle) appendTitleChar();
        else appendContentChar();
        int spaceNums = 0;
        for(String s:strs)
            spaceNums += s.length();
        spaceNums = (isTitle ? titleSpaceNums : contentSpaceNums) - spaceNums;
        appendSpace(spaceNums % 2);
        appendSpace(spaceNums /= 2);
        for(String s:strs) str.append(s);
        appendSpace(spaceNums);
        if(isTitle) appendTitleChar();
        else appendContentChar();
        appendNewLine();
    }

    //添加空格对齐
    private void appendSpace(int nums)
    {
        str.append(" ".repeat(Math.max(0, nums)));
    }

    private void appendNewLine()
    {
        str.append("\n");
    }

    private void appendContentChar()
    {
        str.append(contentChar);
    }

    private void appendTitleChar()
    {
        str.append(titleChar);
    }
}
