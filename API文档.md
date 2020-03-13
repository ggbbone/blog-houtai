# API接口文档
## 文章模块
### 查询文章列表
/api/article/list

post

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
page  | Integer | 选填 | 页码（从1开始）
size  | Integer | 选填 | 每页条数
userId  | Integer | 选填 | 筛选指定用户
categoryId  | Integer | 选填 | 筛选指定标签
orderBy  | Integer | 选填 | 排序方式

出参:
```
{
    "code": 200,
    "message": "success",
    "data": {
        "pageNum": 1,
        "pageSize": 10,
        "hasNext": false,
        "totalPage": 1,
        "total": -1,
        "list": [
            {
                "id": 110651,
                "userId": 28,
                "outline": "",
                "categoryId": 1011,
                "title": "asdasdwadawdad",
                "cover": "",
                "createdDate": "2020-03-05 18:08:43",
                "updatedDate": null,
                "lastCommentTime": "2020-03-05 18:11:55",
                "likeCount": 0,
                "viewCount": 0,
                "commentCount": 0,
                "recommendIndex": 1,
                "hot": false,
                "hotIndex": 0,
                "status": 1,
                "content": null,
                "user": {
                    "userId": 28,
                    "username": "ggbbone",
                    "avatar": "",
                    "blogAddress": "",
                    "collectedEntriesCount": 0,
                    "collectionCount": 0,
                    "followeeCount": 0,
                    "followerCount": 0,
                    "createdDate": "2020-01-07 16:21:50",
                    "updatedDate": "2020-01-07 16:21:50",
                    "outline": ""
                },
                "category": {
                    "title": "数据库",
                    "id": 1011
                },
                "tags": [
                    {
                        "title": "MySQL",
                        "id": 1005
                    },
                    {
                        "title": "Redis",
                        "id": 1006
                    }
                ],
                "hasLike": false
            }
        ]
    }
}
```
### 查询文章详情
/api/article/{articleId}

get

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
article  | Integer | 必填 | 文章id（在url中）

出参:
```
{
    "code": 200,
    "message": "success",
    "data": {
        "id": 110646,
        "userId": null,
        "outline": null,
        "categoryId": null,
        "title": "数据库xxxxx",
        "cover": "",
        "createdDate": "2020-03-05 18:07:54",
        "updatedDate": null,
        "lastCommentTime": "2020-03-12 14:44:47",
        "likeCount": 0,
        "viewCount": 14,
        "commentCount": 0,
        "recommendIndex": 1,
        "hot": false,
        "hotIndex": 0,
        "status": 1,
        "content": "随便写点东西，，，，，，。。。啊啊啊啊啊啊",
        "user": {
            "userId": 28,
            "username": "ggbbone",
            "avatar": "",
            "blogAddress": "",
            "collectedEntriesCount": 0,
            "collectionCount": 0,
            "followeeCount": 0,
            "followerCount": 0,
            "createdDate": "2020-01-07 16:21:50",
            "updatedDate": "2020-01-07 16:21:50",
            "outline": ""
        },
        "category": {
            "title": "后端",
            "id": 1010
        },
        "tags": [],
        "hasLike": false
    }
}
```
### 创建文章
/api/article/create

post

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
title  | Integer | 必填 | 标题
categoryId|Integer|必填| 分类id
content|String|必填| 正文内容
tagsId |Array(Integer)|必填| 标签id数组
cover|String|选填|封面url
在body中：
```
{
	"title": "asdasdwadawdad",
	"categoryId":1011,
	"content":"随便写点东西，，，，，，。。。啊啊啊啊啊啊",
	"tagsId":[1007]
}
```

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 1
}
```
### 更新文章
/api/article/update

post

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
id  | Integer | 必填 | 文章id
title  | Integer | 必填 | 标题
categoryId|Integer|必填| 分类id
content|String|必填| 正文内容
tagsId |Array(Integer)|必填| 标签id数组
cover|String|选填|封面url

在body中：
```
{
	"id":110646,
	"title": "asdasdwadawdad",
	"categoryId":1011,
	"content":"随便写点东西",
	"tagsId":[1008]
}
```

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 1
}
```
### 删除文章
/api/article/delete/{articleId}

post

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
articleId  | Integer | 必填 | 文章id(在url中)

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 1
}
```
## 标签模块
### 查询标签列表
/api/category/list

post

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
page  | Integer | 选填 | 页码（从1开始）
size  | Integer | 选填 | 每页条数
isCategory  | Boolean | 选填 | 是否是分类
isFollow  | Boolean | 选填 | 筛选是否关注

出参:
```
{
    "code": 200,
    "message": "success",
    "data": {
        "pageNum": 1,
        "pageSize": 10,
        "hasNext": false,
        "totalPage": 1,
        "total": -1,
        "list": [
            {
                "id": 1005,
                "title": "MySQL",
                "alias": "",
                "icon": "",
                "background": "",
                "createdDate": "2020-03-05 17:43:19",
                "updatedDate": null,
                "entryCount": 5,
                "followCount": 0,
                "isCategory": false,
                "status": 1
            }
        ]
    }
}
```
### 创建文章标签
/api/category/tag/create

post

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
title  | Integer | 必填 | 标题
alias|String|选填| 英文或拼音
icon|String|选填| 图标url
background |String|选填| 背景图片url

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 1
}
```
### 创建文章分类
/api/category/create

post

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
title  | Integer | 必填 | 标题
alias|String|选填| 英文或拼音
icon|String|选填| 图标url
background |String|选填| 背景图片url

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 1
}
```
### 更新标签
/api/category/update

post

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
id  | Integer | 必填 | 文章id
title|String|必填| 标题
alias|String|选填| 英文或拼音
icon|String|选填| 图标url
background |String|选填| 背景图片url


在body中：
```
{
	"id":110646,
	"title": "asdasdwadawdad",
	"categoryId":1011,
	"content":"随便写点东西",
	"tagsId":[1008]
}
```

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 1
}
```
### 删除标签
/api/category/delete/{categoryId}

post

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
categoryId  | Integer | 必填 | 标签id(在url中)

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 1
}
```
## 草稿模块
### 查询草稿列表
/api/draft/list

get

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
page  | Integer | 选填 | 页码（从1开始）
size  | Integer | 选填 | 每页条数

出参:
```
{{
     "code": 200,
     "message": "success",
     "data": {
         "pageNum": 1,
         "pageSize": 10,
         "hasNext": false,
         "totalPage": 1,
         "total": -1,
         "list": [
             {
                 "id": 67,
                 "userId": 28,
                 "title": "xxxxxtitlexxxxx",
                 "cover": "http://youzijie-1255502425.cos.ap-chengdu.myqcloud.com/sonova/1583468037414.PNG",
                 "createdDate": "2020-03-06 15:04:44",
                 "updatedDate": "2020-03-06 15:05:59",
                 "status": 1,
                 "content": null
             }
         ]
     }
 }
```
### 查询草稿详情
/api/draft/{draftId}

get

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
draftId  | Integer | 必填 | 草稿id（在url中）

出参:
```
{
    "code": 200,
    "message": "success",
    "data": {
        "id": 67,
        "userId": 28,
        "title": "xxxxxtitlexxxxx",
        "cover": "http://youzijie-1255502425.cos.ap-chengdu.myqcloud.com/sonova/1583468037414.PNG",
        "createdDate": "2020-03-06 15:04:44",
        "updatedDate": "2020-03-06 15:05:59",
        "status": 1,
        "content": null
    }
}
```
### 添加草稿
/api/draft/create

post

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
title  | Integer | 必填 | 标题
content|String|必填| 正文内容
cover|String|选填|封面url

在body中：
```
{
    "code": 200,
    "message": "success",
    "data": 68
}
```

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 1
}
```
### 更新草稿
/api/draft/update

post

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
id  | Integer | 必填 | 文章id
title  | Integer | 必填 | 标题
content|String|必填| 正文内容
cover|String|选填|封面url

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 1
}
```
### 删除草稿
/api/draft/delete/{draftId}

post

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
draftId  | Integer | 必填 | 草稿id(在url中)

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 1
}
```
## 关注模块
### 添加关注
/api/user/follow/{type}/{typeId}  

put

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
type  | Integer | 必填 | 关注类型(在url中)
typeId  | Integer | 必填 | 类型id(在url中)

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 1
}
```
### 取消关注
/api/user/follow/{type}/{typeId}  

delete

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
type  | Integer | 必填 | 关注类型(在url中)
typeId  | Integer | 必填 | 类型id(在url中)

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 0
}
```
### 查询用户关注的列表
/api/user/follow/followees

get

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
page  | Integer | 选填 | 页码（从1开始）
size  | Integer | 选填 | 每页条数
userId  | Integer | 选填 | 用户id

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 0
}
```
### 查询用户粉丝列表
/api/user/follow/followers

get

入参：

参数名 | 类型 | 属性 | 描述
---|---|---|---
page  | Integer | 选填 | 页码（从1开始）
size  | Integer | 选填 | 每页条数
userId  | Integer | 选填 | 用户id

出参:
```
{
    "code": 200,
    "message": "success",
    "data": 0
}
```

