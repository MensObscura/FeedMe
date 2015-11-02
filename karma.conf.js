preprocessors = {
    'src/test/resources/*.js': 'coverage'
};


reporters = ['progress', 'junit', 'coverage'];

junitReporter = {
    outputFile: 'target/script/unit.xml',
    suite: 'unit'
};
coverageReporter = {
    type : 'cobertura',
    dir : 'target/coverage/'
}

