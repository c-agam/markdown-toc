/*
 * Copyright (c)  2018. houbinbin Inc.
 * markdown-toc All rights reserved.
 */

package com.github.houbb.markdown.toc.support;

import com.github.houbb.markdown.toc.support.md.MarkdownContentToc;
import com.github.houbb.markdown.toc.support.md.impl.AtxMarkdownContentToc;
import com.github.houbb.markdown.toc.util.CollectionUtil;
import com.github.houbb.markdown.toc.util.FileUtil;
import com.github.houbb.markdown.toc.util.TestPathUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * <p> atx 内容测试 </p>
 *
 * <pre> Created: 2018/8/15 下午3:02  </pre>
 * <pre> Project: markdown-toc  </pre>
 *
 * @author houbinbin
 * @version 1.0
 * @since JDK 1.7
 */
public class AtxMarkdownContentTocTest {


    private MarkdownContentToc markdownContentToc;

    @Before
    public void before() {
        markdownContentToc = new AtxMarkdownContentToc();
    }

    @Test
    public void emptyTest() {
        List<String> lines = getLines("empty.md");
        List<String> tocs = markdownContentToc.getTocLines(lines, true);
        Assert.assertTrue(CollectionUtil.isEmpty(tocs));
    }

    @Test
    public void emptyContentTest() {
        List<String> lines = getLines("empty-content.md");
        List<String> tocs = markdownContentToc.getTocLines(lines, true);
        Assert.assertTrue(CollectionUtil.isEmpty(tocs));
    }

    @Test
    public void commonTest() {
        List<String> lines = getLines("common.md");
        List<String> tocs = markdownContentToc.getTocLines(lines, true);
        showTocs(tocs);
    }

    @Test
    public void nLevelTest() {
        List<String> lines = getLines("N-LEVEL.md");
        List<String> tocs = markdownContentToc.getTocLines(lines, true);
        showTocs(tocs);
    }

    /**
     * 展现目录
     * @param tocs toc lines
     */
    private void showTocs(final List<String> tocs) {
        Assert.assertFalse(CollectionUtil.isEmpty(tocs));
        for(String string : tocs) {
            System.out.println(string);
        }
    }

    /**
     * 获取文件内容
     * @param filePath 文件路径
     * @return 文件内容列表
     */
    private List<String> getLines(final String filePath) {
        String fullPath = TestPathUtil.getAppRootPath(filePath);
        try {
            return FileUtil.getFileContentEachLine(new FileInputStream(new File(fullPath)), 0);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
