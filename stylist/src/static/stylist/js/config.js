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
        .state('image',{
            url: "/image?imageId",
            templateUrl: "views/write_steps/pointedimage.html",
            data: { pageTitle: 'Taggified Image', specialClass: 'gray-bg' }
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
        
        .state('forgot_password', {
            url: "views/account/forgot_password",
            templateUrl: "views/forgot_password.html",
            data: { pageTitle: 'Forgot password', specialClass: 'gray-bg' }
        })

       .state('app', {
            abstract: true,
            url: "/app",
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
        
        .state('withdraw', {
            abstract: true,
            url: "/withdraw",
            templateUrl: "views/common/content.html",
        })
        .state('withdraw.withdraw_list', {
            url: "/withdraw_list",
            templateUrl: "views/commission/withdraw_list.html",
            data: { pageTitle: 'withdraw list' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/footable/footable.all.min.js', 'css/plugins/footable/footable.core.css']
                        },
                        {
                            name: 'ui.footable',
                            files: ['js/plugins/footable/angular-footable.js']
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

        .state('app.write', {
            url: "/write",
            templateUrl: "views/write_steps/write.html",
            controller: wizardCtrl,
            data: { pageTitle: 'Write' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: [
                            'css/plugins/steps/jquery.steps.css',
                            ]
                        },
                        {
                            files: ['js/plugins/crop/main.css','js/plugins/crop/cropper.min.css']
                        },
                    ]);
                }
            }
        })
        .state('app.write.step_one', {
            url: '/step_one',
            templateUrl: 'views/write_steps/step_one.html',
            data: { pageTitle: 'Write Article' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/summernote/summernote.css','css/plugins/summernote/summernote-bs3.css','js/plugins/summernote/summernote.min.js']
                        },
                        {
                            name: 'summernote',
                            files: ['css/plugins/summernote/summernote.css','css/plugins/summernote/summernote-bs3.css','js/plugins/summernote/summernote.min.js','js/plugins/summernote/angular-summernote.min.js']
                        }
                    ]);
                }
            }
        })
        .state('app.write.step_two', {
            url: '/step_two',
            templateUrl: 'views/write_steps/step_two.html',
            data: { pageTitle: 'Write Article' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['css/plugins/slick/slick.css','css/plugins/slick/slick-theme.css','js/plugins/slick/slick.min.js']
                        },
                        {
                            name: 'slick',
                            files: ['js/plugins/slick/angular-slick.min.js']
                        },
                        {
                            name: 'ui.select',
                            files: ['js/plugins/ui-select/select.min.js', 'css/plugins/ui-select/select.min.css']
                        }
                    ]);
                }
            }
        })
        .state('app.write.step_three', {
            url: '/step_three',
            templateUrl: 'views/write_steps/step_three.html',
            data: { pageTitle: 'Write Article' }
        })

        .state('pages', {
            abstract: true,
            url: "/pages",
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
        .state('pages.drafts', {
            url: "/drafts",
            templateUrl: "views/list_article/articleDrafts.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('pages.viewArticle', {
            url: "/viewArticle",
            templateUrl: "views/list_article/viewArticle.html",
            data: { pageTitle: 'Drafts' }
        })
        .state('pages.submitted', {
            url: "/submitted",
            templateUrl: "views/list_article/articleSubmitted.html",
            data: { pageTitle: 'submitted' }
        })
        .state('pages.approved', {
            url: "/approved",
            templateUrl: "views/list_article/articleApproved.html",
            data: { pageTitle: 'Approved' }
        })
        .state('pages.rejected', {
            url: "/rejected",
            templateUrl: "views/list_article/articleRejected.html",
            data: { pageTitle: 'Rejected' }
        })

        .state('statistics', {
            abstract: true,
            url: "/statistics",
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
        .state('statistics.article', {
            url: "/articleStats",
            templateUrl: "views/statistics/articleStats.html",
            data: { pageTitle: 'Article Statistics' }
        })
        .state('statistics.articleDaily', {
            url: "/articleStatsDaily",
            templateUrl: "views/statistics/articleStatsDaily.html",
            data: { pageTitle: 'Article Statistics Daily' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            name: 'angular-flot',
                            files: [ 'js/plugins/flot/jquery.flot.js', 'js/plugins/flot/jquery.flot.time.js', 'js/plugins/flot/jquery.flot.tooltip.min.js', 'js/plugins/flot/jquery.flot.spline.js', 'js/plugins/flot/jquery.flot.resize.js', 'js/plugins/flot/jquery.flot.pie.js', 'js/plugins/flot/curvedLines.js', 'js/plugins/flot/angular-flot.js', ]
                        },
                        {
                            name: 'ui.checkbox',
                            files: ['js/plugins/bootstrap/angular-bootstrap-checkbox.js']
                        }
                    ]);
                }
            }
        })
        .state('statistics.commission', {
            url: "/commissionStats",
            templateUrl: "views/statistics/commissionStats.html",
            data: { pageTitle: 'Commission Statistics' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            name: 'angular-flot',
                            files: [ 'js/plugins/flot/jquery.flot.js', 'js/plugins/flot/jquery.flot.time.js', 'js/plugins/flot/jquery.flot.tooltip.min.js', 'js/plugins/flot/jquery.flot.spline.js', 'js/plugins/flot/jquery.flot.resize.js', 'js/plugins/flot/jquery.flot.pie.js', 'js/plugins/flot/curvedLines.js', 'js/plugins/flot/angular-flot.js', ]
                        },
                        {
                            name: 'ui.checkbox',
                            files: ['js/plugins/bootstrap/angular-bootstrap-checkbox.js']
                        }
                    ]);
                }
            }
        })

        .state('information', {
            abstract: true,
            url: "/information",
            templateUrl: "views/common/content.html",
        })
        .state('information.guideCreateArticle', {
            url: "/guideCreateArticle",
            templateUrl: "views/information/guideCreateArticle.html",
            data: { pageTitle: 'guideCreateArticle' }
        })
        .state('information.faq', {
            url: "/faq",
            templateUrl: "views/information/faq.html",
            data: { pageTitle: 'FAQ' }
        })
        .state('information.BloggerGrowthSystem', {
            url: "/BloggerGrowthSystem",
            templateUrl: "views/information/BloggerGrowthSystem.html",
            data: { pageTitle: 'BloggerGrowthSystem' }
        })

        .state('images', {
            abstract: true,
            url: "/images",
            templateUrl: "views/common/content.html"
        })
        .state('images.taggifiedImages', {
            url: "/taggifiedImgaes",
            templateUrl: "views/write_steps/taggifiedimages.html",
            data: { pageTitle: 'MODE | Shop With Stylist' }
        })

        .state('items', {
            abstract: true,
            url: "/items",
            templateUrl: "views/common/content.html"
        })
        .state("items.select", {
            url: "/select",
            templateUrl: "views/items/SelectItem.html",
            data: { pageTitle: 'MODE | Shop With Stylist' },
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
        .state("items.cart", {
            url: "/cart",
            templateUrl: "views/items/ItemCart.html",
            data: { pageTitle: 'MODE | Shop With Stylist' },
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
        .state("items.order", {
            url: "/order",
            templateUrl: "views/items/ItemOrder.html",
            data: { pageTitle: 'MODE | Shop With Stylist' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/footable/footable.all.min.js', 'css/plugins/footable/footable.core.css']
                        },
                        {
                            name: 'ui.footable',
                            files: ['js/plugins/footable/angular-footable.js']
                        }
                    ]);
                }
            }
        })
        .state("items.list", {
            url: "/list",
            templateUrl: "views/items/YourItems.html",
            data: { pageTitle: 'MODE | Shop With Stylist' },
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

        .state("items.detail", {
            url: "/detail",
            templateUrl: "views/items/ItemDetail.html",
            data: { pageTitle: 'MODE | Shop With Stylist' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['css/plugins/slick/slick.css','css/plugins/slick/slick-theme.css','js/plugins/slick/slick.min.js']
                        },
                        {
                            name: 'slick',
                            files: ['js/plugins/slick/angular-slick.min.js']
                        },
                        {
                            insertBefore: '#loadBefore',
                            name: 'localytics.directives',
                            files: ['css/plugins/chosen/chosen.css','js/plugins/chosen/chosen.jquery.js','js/plugins/chosen/chosen.js']
                        }
                    ]);
                }
            }
        })
    ;
}

angular
    .module('inspinia')
    .config(config)
    .run(function($rootScope, $state) {
        $rootScope.$state = $state;
    });

