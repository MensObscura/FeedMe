clean: {
   dist: {
     files: [{
        dot: true,

        src: [
            '.tmp',
            '<%= yeoman.dist %>/*',
            '!<%= yeoman.dist %>/.git*'
            ]}]
   },
   test: {
        files: [{src: ['target']}]
   }
}

grunt.registerTask('test',
   'clean:test',
   'karma'
]);
