// priority: 0

console.info('Hello, World! (You will only see this line once in console, during startup)')

onEvent('block.registry', event => {
	event.create('example_cogwheel', 'cogwheel')
	    .material('wood')
	    .displayName('Example Cogwheel');
    event.create('large_example_cogwheel', 'large_cogwheel')
        .material('wood')
        .displayName('Example Cogwheel');
})