const { Router } = require('express');
const router = new Router();
const _ = require('underscore');
const bodyParser = require("body-parser");

var graphs = require('../sample.json');

router.use(bodyParser.json());

router.get('/', (req, res) => {
    res.json(graphs);
});

router.post('/', (req, res) => {
    const id = graphs.length + 1;
    const { nodes, edges } = req.body;
    const newGraph = { ...req.body, id };
    if (id && nodes && edges) {
        graphs.push(newGraph);
        res.json(graphs);
    } else {
        res.status(500).json({error: 'There was an error.'});
    }
});

router.delete('/', (req, res) => {
    graphs = [];
        res.json(req.body.data);
    
});

router.put('/', (req, res) => {
    //const { id } = req.params;
    const { id, nodes, edges } = req.body;
    if (id && nodes && edges) {
        _.each(graphs, (graph) => {
            if (graph === graph) {
                graph.id = id;
                graph.node = nodes;
                graph.edges= edges;
        
            }
        });
        res.json(graphs);
    } else {
        res.status(500).json({error: 'There was an error.'});
    }
});

router.get('/:id', (req, res) => {
    // Reading id from the URL
    const id = req.params.id;

    // Searching graph for the id
    for (let graph of graphs) {
        if (graph.id === id) {
            res.json(graph);
            return;
        }
    }

    // Sending 404 when not found something is a good practice
    res.status(404).send('Graph not found');
});

router.put('/:id', (req, res) => {
    const { id } = req.params;
    const { nodes, edges } = req.body;
    if (id && nodes && edges) {
        _.each(graphs, (graph, i) => {
            if (graph.id === id) {
                graph.node = nodes;
                graph.edges= edges;
        
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
        _.each(graphs, (graph, i) => {
            if (graph.id == id) {
                graphs.splice(i, 1);
            }
        });
        res.json(graphs);
    }
});

module.exports = router;