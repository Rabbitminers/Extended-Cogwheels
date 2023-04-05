// priority: 0

console.info('Hello, World! (You will only see this line once in console, during startup)')

onEvent('block.registry', event => {
	event.create('example_cogwheel', 'cogwheel')
	    .material('wood')
	    .displayName('Example Cogwheel');

    event.create('large_example_cogwheel', 'large_cogwheel')
        .material('wood')
        .displayName('Large Example Cogwheel');

    event.create('example_half_shaft_cogwheel', 'half_shaft_cogwheel')
        .material('wood')
        .displayName('Example Half Shaft Cogwheel');

    event.create('large_example_half_shaft_cogwheel', 'large_half_shaft_cogwheel')
        .material('wood')
        .displayName('Large Example Half Shaft Cogwheel');

    event.create('example_shaftless_cogwheel', 'shaftless_cogwheel')
        .material('wood')
        .displayName('Example Shaftless Cogwheel');

    event.create('large_example_shaftless_cogwheel', 'large_shaftless_cogwheel')
        .material('wood')
        .displayName('Large Example Shaftless Cogwheel');
})