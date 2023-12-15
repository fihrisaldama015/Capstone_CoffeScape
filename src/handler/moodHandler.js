const reccomentation = require('./moodBased');

const drink = async (request, h) => {
    try {
        const { mood } = request.params;
        const result = reccomentation.suggestDrink(mood);
        const response = h.response({
            status: 'success',
            message: 'get drink successfully',
            data: {
                result,
            },
        });
        response.code(200);
        return response;
    } catch (error) {
        console.log(error);
        const response = h.response({
            status: 'fail',
            message: 'get drink failed',
        });
        response.code(500);
        return response;
    }
}

module.exports = { drink };