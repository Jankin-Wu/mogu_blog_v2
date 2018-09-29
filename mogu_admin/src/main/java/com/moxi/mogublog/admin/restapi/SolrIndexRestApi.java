package com.moxi.mogublog.admin.restapi;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moxi.mogublog.admin.global.SysConf;
import com.moxi.mogublog.utils.ResultUtil;
import com.moxi.mogublog.xo.service.BlogSearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 * 索引维护api
 * @author limboy
 * @create 2018-09-29 16:25
 */
@RestController
@RequestMapping("/search")
@Api(value="索引维护RestApi",tags={"solrIndexRestApi"})
public class SolrIndexRestApi {

    @Autowired
    private BlogSearchService blogSearchService;

    private static Logger log = LogManager.getLogger(SolrIndexRestApi.class);

    /*@ApiOperation(value="搜索Blog", notes="搜索Blog")
    @GetMapping("/searchBlog")
    public String searchBlog(HttpServletRequest request,
                             @ApiParam(name = "keywords", value = "关键字",required = true)@RequestParam(required=true)String keywords) {

        Map<String,Object> map = blogSearchService.search(keywords);
        return ResultUtil.result(SysConf.SUCCESS, map);

    }
    */

    @ApiOperation(value="初始化solr索引", notes="初始化solr索引")
    @PostMapping("/initIndex")
    public String initIndex(HttpServletRequest request){

        blogSearchService.initIndex();
        log.info("初始化索引成功");
        return ResultUtil.result(SysConf.SUCCESS,"初始化索引成功");

    }

    @ApiOperation(value="添加solr索引", notes="添加solr索引")
    @PostMapping("/addIndex")
    public String addIndex(HttpServletRequest request,
                           @ApiParam(name="uid", value="博客uid",required=true)@RequestParam(name = "uid", required = true)String uid,
                           @ApiParam(name = "title", value = "博客标题",required = false) @RequestParam(name = "title", required = false) String title,
                           @ApiParam(name = "summary", value = "博客简介",required = false) @RequestParam(name = "summary", required = false) String summary,
                           @ApiParam(name = "tagUid", value = "标签UID",required = false) @RequestParam(name = "tagUid", required = false) String tagUid,
                           @ApiParam(name = "blogSortUid", value = "博客分类UID",required = false) @RequestParam(name = "blogSortUid", required = false) String blogSortUid,
                           @ApiParam(name = "author", value = "作者",required = false) @RequestParam(name = "author", required = true) String author){

        blogSearchService.addIndex(uid, title, summary, tagUid, blogSortUid, author);
        log.info("新建solr索引");
        return ResultUtil.result(SysConf.SUCCESS,"新建solr索引成功");

    }


    @ApiOperation(value="更新solr索引", notes="更新solr索引")
    @PostMapping("/updateIndex")
    public String updateIndex(HttpServletRequest request,
                              @ApiParam(name="uid", value="博客uid",required=true)@RequestParam(name = "uid", required = true)String uid,
                              @ApiParam(name = "title", value = "博客标题",required = false) @RequestParam(name = "title", required = false) String title,
                              @ApiParam(name = "summary", value = "博客简介",required = false) @RequestParam(name = "summary", required = false) String summary,
                              @ApiParam(name = "tagUid", value = "标签UID",required = false) @RequestParam(name = "tagUid", required = false) String tagUid,
                              @ApiParam(name = "blogSortUid", value = "博客分类UID",required = false) @RequestParam(name = "blogSortUid", required = false) String blogSortUid,
                              @ApiParam(name = "author", value = "作者",required = false) @RequestParam(name = "author", required = true) String author){

        blogSearchService.updateIndex(uid, title, summary, tagUid, blogSortUid, author);

        log.info("更新solr索引");
        return ResultUtil.result(SysConf.SUCCESS,"更新solr索引成功");

    }

    @ApiOperation(value="删除solr索引", notes="删除solr索引")
    @PostMapping("/deleteIndex")
    public String deleteIndex(HttpServletRequest request,
                              @ApiParam(name="uid", value="博客uid",required=true)@RequestParam(name = "uid", required = true)String uid){

        blogSearchService.deleteIndex(uid);
        log.info("删除部分索引");
        return ResultUtil.result(SysConf.SUCCESS,"删除索引成功");

    }

    @ApiOperation(value="删除全部solr索引", notes="删除全部solr索引")
    @PostMapping("/deleteAllIndex")
    public String deleteAllIndex(HttpServletRequest request){

        blogSearchService.deleteAllIndex();
        log.info("删除所有索引");
        return ResultUtil.result(SysConf.SUCCESS,"删除所有索引成功");
    }


}
