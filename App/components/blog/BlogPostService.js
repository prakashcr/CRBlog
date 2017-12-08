
var BlogPostModule = angular.module('BlogPostModule', [])
BlogPostModule.service('BlogPostService', ['REST_URI', '$http', function (REST_URI, $http) {

    this.saveBlogPost = function (blogPost, username) {
        console.log('content reached to blogpost service');
        return $http.post(REST_URI + '/saveblogpost/' + username, blogPost)
    }


    this.listOfBlogsWaitingForApproval = function (blogPost) {
        console.log('content reached to getall blogpost service');
        return $http.get(REST_URI + '/getallblogs/' + 0)
    }

    this.listOfBlogsApproved = function (blogPost) {
        console.log('content reached to list of approved blogpost service');
        return $http.get(REST_URI + '/getallblogs/' + 1)
    }

    this.getBlogPostById = function (id, blogPost) {
        console.log('content reached to blogpost by idd service');
        return $http.get(REST_URI + '/getblogpost/' + id, blogPost)
    }

    this.updateApproval = function (blogPost) {
        console.log(blogPost)
        console.log('content reached to update blogpost service');
        return $http.put(REST_URI + "/updateblogpost", blogPost)
    }

    this.getApprovalStatus = function (blogPost, username) {
        console.log('content reached approval to blogpost service');
        return $http.get(REST_URI + "/blogpostapprovalstatus/" + username, blogPost)
    }
    this.addComment = function (blogComment, username) {
        console.log(blogComment)
        return $http.post(REST_URI + "/addcomment/" + username, blogComment)
    }
    this.getBlogComments = function (blogPostId, blogComment) {
        return $http.get(REST_URI + "/getcomments/" + blogPostId, blogComment)
    }
    

}]);    