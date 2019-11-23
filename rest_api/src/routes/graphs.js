const { Router } = require('express');
const router = new Router();
const _ = require('underscore');

const graphs = require('../sample.json');

router.get('/', (req, res) => {
    res.json(graphs);
});

router.post('/', (req, res) => {
    const id = graphs.length + 1;
    const { title, director, year, rating } = req.body;
    const newGraph = { ...req.body, id };
    if (id && title && director && year && rating) {
        graphs.push(newGraph);
        res.json(graphs);
    } else {
        res.status(500).json({error: 'There was an error.'});
    }
});

router.put('/:id', (req, res) => {
    const { id } = req.params;
    const { title, director, year, rating } = req.body;
    if (id && title && director && year && rating) {
        _.each(graphs, (graphs, i) => {
            if (graphs.id === id) {
                graphs.node = node;
                graphs.edge= edge;
        
            }
        });
        res.json(graphs);
    } else {
        res.status(500).json({error: 'There was an error.'});
    }
});

router.delete('/:id', (req, res) => {
    const {id} = req.params;
    if (id) {
        _.each(graphs, (graphs, i) => {
            if (graphs.id == id) {
                graphs.splice(i, 1);
            }
        });
        res.json(graphs);
    }
});

module.exports = router;