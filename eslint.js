module.exports = {
    extends: 'airbnb-base',
    env: {
        commonjs: true,
        node: true,
        mocha: true,
    },
    rules: {
        'global-require': 'off',
        indent: ['error', 4],
        'no-param-reassign': 'off',
    },
};