'use strict';

module.exports = function(grunt) {
    
    // Load grunt tasks automatically
    require('load-grunt-tasks')(grunt);

    var environmentConfig = {
        root: 'dev/source',
        app: 'dev/source/app',
        dist: 'dist',
        static: 'dist',
        bower: 'bower_components'
    };

    grunt.initConfig({
        environment: environmentConfig,
        compass: {
            options: {
                sassDir: '<%= environment.app %>/css',
                cssDir: '<%= environment.dist %>/css',
                importPath: '<%= environment.bower %>',
                relativeAssets: false,
                fontsDir: '<%= environment.app %>/assets/fonts',
            },
            dist: {
                options: {
                    outputStyle: 'compressed',
                }
            },
            build: {
                options: {
                    outputStyle: 'nested',
                    cssDir: ['<%= environment.static %>/css'],
                    httpImagesPath: '../assets/images',
                    httpGeneratedImagesPath: '../assets/images/generated',
                    httpFontsDir: '../assets/fonts',
                }
            }
        },
        bake: {
            build: {
                files: [
                    {
                        src: ['<%= environment.app %>/html/pages/*.html'],
                        dest: '<%= environment.static %>',
                        expand: true,
                        flatten: true
                    }
                ]
            },
        },
        express: {
            test: {
                options: {
                    port: 9002,
                    hostname: 'localhost',
                    bases: [
                        '.tmp',
                        'test',
                        '<%= environment.dist %>'
                        ]
                }
            },
            static: {
                options: {
                    port: 9000,
                    hostname: 'localhost',
                    livereload: true,
                    bases: '<%= environment.static %>/',
                }
             },
        },
        concat: {
            bootstrap: {
                src: [
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/transition.js',
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/alert.js',
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/button.js',
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/carousel.js',
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/collapse.js',
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/datepicker.js',
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/dropdown.js',
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/modal.js',
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/tooltip.js',
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/popover.js',
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/scrollspy.js',
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/tab.js',
                    '<%= environment.bower %>/bootstrap-sass-official/assets/javascripts/bootstrap/affix.js',
                    '<%= environment.bower %>/bootstrap-sass-datepicker/js/bootstrap-sass-datepicker.js',
                    '<%= environment.bower %>/jasny-bootstrap/dist/js/jasny-bootstrap.min.js'
                ],
                dest: '.tmp/js/bootstrap.js'
            },
            angular: {
                src: [
                    '<%= environment.bower %>/angularjs/angular.min.js'
                ],
                dest: '.tmp/js/angular.min.js'
            },
            jquery: {
                src: [
                    '<%= environment.bower %>/jquery/dist/jquery.min.js'
                ],
                dest: '.tmp/js/jquery.min.js'
            },
            jqueryui: {
                src: [
                    '<%= environment.bower %>/jquery-ui/jquery-ui.min.js'
                ],
                dest: '.tmp/js/jquery-ui.min.js'
            }
        },
        clean: {
            dist: {
                options: {
                    force: true,
                },
                files: [{
                    dot: true,
                    src: ['.tmp', '<%= environment.dist %>/*', '!<%= environment.dist %>/.git*']
                }]
            },
            server: '.tmp'
        },
        jshint: {
            options: {
                jshintrc: '.jshintrc',
                reporter: require('jshint-stylish')
            },
            all: {
                src: ['Gruntfile.js', '<%= environment.app %>/js/{,*/}*.js']
            },
        },
        copy: {
            dist: {
                files: [{
                    expand: true,
                    flatten: true,
                    dest: '<%= environment.dist %>/js',
                    cwd: '.tmp/js/',
                    src: ['**']
                },
                {
                    expand: true,
                    flatten: false,
                    cwd: '<%= environment.app %>/assets/fonts/',
                    dest: '<%= environment.dist %>/assets/fonts/',
                    src: ['**']
                },
                {
                    expand: true,
                    flatten: false,
                    cwd: '<%= environment.app %>/data/',
                    dest: '<%= environment.dist %>/data/',
                    src: ['**']
                },
                {
                    expand: true,
                    flatten: false,
                    cwd: '<%= environment.app %>/assets/images/',
                    dest: '<%= environment.dist %>/assets/images/',
                    src: ['**']
                }]
            }
        },
        watch: {
            static: {
                files: ['<%= environment.app %>/html/{,**/}*.html', '<%= environment.app %>/css/{,*/}*.scss', '<%= environment.app %>/js/**'],
                tasks: ['build'],
                options: {
                    livereload: true
                }
            },
        }
    });
    
    grunt.registerTask('static', [
        'clean:dist',
        'build',
        'express:static',
        'watch:static'
    ]);

    grunt.registerTask('build', [
        'clean:dist',
        'jshint',
        'compass:build',
        'bake:build',
        'concat',
        'copy:dist'
    ]);
};