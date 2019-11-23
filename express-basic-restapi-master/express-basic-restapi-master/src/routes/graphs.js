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
    res.status(500).send('Graph not found');
});

router.get('/:id/nodes/:id2', (req, res) => { 
    const id = req.params.id;
    const id2 = req.params.id;
    // Searching node for the id
    for (let graph of graphs) {
        if (graph.id === id) {
            for (let node of graph.nodes) {
                if (node.id === id2) {
                    res.json(node);
                    return;
                }
        }   }
    
    }
});

router.get('/:id/nodes', (req, res) => { 
    const id = req.params.id;
    // Searching node for the id
    for (let graph of graphs) {
        if (graph.id === id) {
            res.json(graph.nodes);
            return;
        }
    
    }
});

router.post('/:idg/nodes/', (req, res) => {
    const idg = req.params.idg;
    
    for (let graph of graphs) {
        if (graph.id === idg) {
            //const id2 = graph.nodes.length + 1;
            const {id, inDegree, outDegree, entity } = req.body;
            const newNode = { ...req.body };

            if (id && inDegree && outDegree && entity) {
                graph.nodes.push(newNode);
                res.json(graph.nodes);
            } else {
                res.status(500).json({error: 'There was an error.'});
            }
        }
    }
});



router.delete('/:id/nodes/:id2', (req, res) => { 
    const id = req.params.id;
    const id2 = req.params.id;
    // Searching node for the id
    for (let graph of graphs) {
        if (graph.id === id) {
            var cont = 0
            for (let node of graph.nodes) {
                
                if (node.id === id2) {
                    graph.nodes.splice(cont, 1);
                    res.json(graph.nodes);
                }
                cont+=1
        }   }
    
    }
});

router.delete('/:id/nodes/', (req, res) => { 
    const id = req.params.id;
    
    // Searching node for the id
    for (let graph of graphs) {
        if (graph.id === id) {
           graph.nodes = [];
           graph.edges = [];
           res.json(graph.nodes);
           
        }
    
    }
});

router.put('/:id/nodes/:id2', (req, res) => { 
    const id = req.params.id;
    const id2 = req.params.id;
    const num = req.query.num;
    // Searching node for the id
    for (let graph of graphs) {
        if (graph.id === id) {
            for (let node of graph.nodes) {
                if (node.id === id2) {
                    node.entity = num
                    res.json(node);
                    return;
                }
        }   }
    
    }
});

module.exports = router;