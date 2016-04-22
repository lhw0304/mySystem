/**
 * INSPINIA - Responsive Admin Theme
 *
 * Inspinia theme use AngularUI Router to manage routing and views
 * Each view are defined as state.
 * Initial there are written state for all view in theme.
 *
 */
function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider, IdleProvider, KeepaliveProvider, $httpProvider) {

    // Configure Idle settings
    IdleProvider.idle(5); // in seconds
    IdleProvider.timeout(120); // in seconds

    $httpProvider.interceptors.push('httpInterceptor');

    $urlRouterProvider.otherwise("/login");

    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: false
    });

    $stateProvider
        .state('forgot_password', {
            url: "views/account/forgot_password",
            templateUrl: "views/forgot_password.html",
            data: { pageTitle: 'Forgot password', specialClass: 'gray-bg' }
        })
        .state('login', {
            url: "/login",
            templateUrl: "views/account/login.html",
            data: { pageTitle: 'Login', specialClass: 'gray-bg' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/md5/md5.min.js']
                        },
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        }
                    ]);
                }
            }
        })
        .state('home', {
            abstract: true,
            url: "/home",
            templateUrl: "views/common/content.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        }
                    ]);
                }
            }
        })
        .state('home.changepassword', {
            url: "/password",
            templateUrl: "views/profile/changepassword.html",
            data: { pageTitle: 'Profile' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/md5/md5.min.js']
                        }
                    ]);
                }
            }
        })
		.state('home.updateprofile', {
            url: "/updateprofile",
            templateUrl: "views/profile/updateprofile.html",
            data: { pageTitle: 'Profile' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/previewImage/preview.js']
                        },
                        {
                            files: ['js/plugins/city-data/city-data.js']
                        }
                    ]);
                }
            }
        })
        .state('home.profile', {
            url: "/profile",
            templateUrl: "views/profile/profile.html",
            data: { pageTitle: 'Profile'},
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/sparkline/jquery.sparkline.min.js']
                        }
                    ]);
                }
            }
        })
        
        .state('knowledge', {
            abstract: true,
            url: "/knowledge",
            templateUrl: "views/common/content.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/sweetalert/sweetalert.min.js', 'css/plugins/sweetalert/sweetalert.css']
                        },
                        {
                            name: 'oitozero.ngSweetAlert',
                            files: ['js/plugins/sweetalert/angular-sweetalert.min.js']
                        }
                    ]);
                }
            }
        })
        .state('knowledge.upload', {
            url: "/upload",
            templateUrl: "views/knowledge/knowledgeUpload.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('knowledge.list', {
            url: "/list",
            templateUrl: "views/knowledge/knowledgeList.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('knowledge.update', {
            url: "/update",
            templateUrl: "views/knowledge/knowledgeUpdate.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('check', {
            abstract: true,
            url: "/check",
            templateUrl: "views/common/content.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/sweetalert/sweetalert.min.js', 'css/plugins/sweetalert/sweetalert.css']
                        },
                        {
                            name: 'oitozero.ngSweetAlert',
                            files: ['js/plugins/sweetalert/angular-sweetalert.min.js']
                        }
                    ]);
                }
            }
        })
        .state('check.upload', {
            url: "/upload",
            templateUrl: "views/check/checkUpload.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('check.list', {
            url: "/list",
            templateUrl: "views/check/checkList.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('check.update', {
            url: "/update",
            templateUrl: "views/check/checkUpdate.html",
            data: { pageTitle: 'Drafts' }
        })
        
        .state('completion', {
            abstract: true,
            url: "/completion",
            templateUrl: "views/common/content.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/sweetalert/sweetalert.min.js', 'css/plugins/sweetalert/sweetalert.css']
                        },
                        {
                            name: 'oitozero.ngSweetAlert',
                            files: ['js/plugins/sweetalert/angular-sweetalert.min.js']
                        }
                    ]);
                }
            }
        })
        .state('completion.upload', {
            url: "/upload",
            templateUrl: "views/completion/completionUpload.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('completion.list', {
            url: "/list",
            templateUrl: "views/completion/completionList.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('completion.update', {
            url: "/update",
            templateUrl: "views/completion/completionUpdate.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('single', {
            abstract: true,
            url: "/single",
            templateUrl: "views/common/content.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/sweetalert/sweetalert.min.js', 'css/plugins/sweetalert/sweetalert.css']
                        },
                        {
                            name: 'oitozero.ngSweetAlert',
                            files: ['js/plugins/sweetalert/angular-sweetalert.min.js']
                        }
                    ]);
                }
            }
        })
        .state('single.upload', {
            url: "/upload",
            templateUrl: "views/single/singleUpload.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('single.list', {
            url: "/list",
            templateUrl: "views/single/singleList.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('single.update', {
            url: "/update",
            templateUrl: "views/single/singleUpdate.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('multi', {
            abstract: true,
            url: "/multi",
            templateUrl: "views/common/content.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/sweetalert/sweetalert.min.js', 'css/plugins/sweetalert/sweetalert.css']
                        },
                        {
                            name: 'oitozero.ngSweetAlert',
                            files: ['js/plugins/sweetalert/angular-sweetalert.min.js']
                        }
                    ]);
                }
            }
        })
        .state('multi.upload', {
            url: "/upload",
            templateUrl: "views/multi/multiUpload.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('multi.list', {
            url: "/list",
            templateUrl: "views/multi/multiList.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('multi.update', {
            url: "/update",
            templateUrl: "views/multi/multiUpdate.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('short', {
            abstract: true,
            url: "/short",
            templateUrl: "views/common/content.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/sweetalert/sweetalert.min.js', 'css/plugins/sweetalert/sweetalert.css']
                        },
                        {
                            name: 'oitozero.ngSweetAlert',
                            files: ['js/plugins/sweetalert/angular-sweetalert.min.js']
                        }
                    ]);
                }
            }
        })
        .state('short.upload', {
            url: "/upload",
            templateUrl: "views/short/shortUpload.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('short.list', {
            url: "/list",
            templateUrl: "views/short/shortList.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('short.update', {
            url: "/update",
            templateUrl: "views/short/shortUpdate.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('group', {
            abstract: true,
            url: "/group",
            templateUrl: "views/common/content.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/sweetalert/sweetalert.min.js', 'css/plugins/sweetalert/sweetalert.css']
                        },
                        {
                            name: 'oitozero.ngSweetAlert',
                            files: ['js/plugins/sweetalert/angular-sweetalert.min.js']
                        }
                    ]);
                }
            }
        })
        .state('group.create', {
            url: "/create",
            templateUrl: "views/group/group.html",
            data: { pageTitle: 'group' }
        })

        .state('information', {
            abstract: true,
            url: "/information",
            templateUrl: "views/common/content.html",
        })
        .state('information.faq', {
            url: "/faq",
            templateUrl: "views/information/faq.html",
            data: { pageTitle: 'FAQ' }
        })

        .state('admin', {
            abstract: true,
            url: "/admin",
            templateUrl: "views/admin/content.html",
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/md5/md5.min.js']
                        },
                    ]);
                }
            }
        })
        .state('admin.addAccount', {
            url: "/addAccount",
            templateUrl: "views/admin/addAccount.html",
            data: { pageTitle: 'FAQ' }
        })
        .state('admin.resetPassword', {
            url: "/resetPassword",
            templateUrl: "views/admin/reset_password.html",
        })
        .state('admin.deleteAccount', {
            url: "/deleteAccount",
            templateUrl: "views/admin/deleteAccount.html",
        })
        .state('admin.check', {
            url: "/check",
            templateUrl: "views/admin/check.html",
        })
        .state('admin.completion', {
            url: "/completion",
            templateUrl: "views/admin/completion.html",
        })
        .state('admin.single', {
            url: "/single",
            templateUrl: "views/admin/single.html",
        })
        .state('admin.multi', {
            url: "/multi",
            templateUrl: "views/admin/multi.html",
        })
        .state('admin.short', {
            url: "/short",
            templateUrl: "views/admin/short.html",
        })
}

angular
    .module('inspinia')
    .config(config)
    .run(function($rootScope, $state) {
        $rootScope.$state = $state;
    });

