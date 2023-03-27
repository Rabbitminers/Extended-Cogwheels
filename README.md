# Extended Cogwheels (Multiloader)

[Forge & Fabric, 1.18.2 & 1.19.2] Adding more cogwheels to the createmod aswell as providing progression systems to modpack developers through configurations

### KubeJS Integration

As of version 2.1 and above extended cogwheels has allows for you to create your own custom cogwheels easily

Cogwheels can now be created like any other block just specify the type and properties give it a texture and try it out in game

Example Adding A Small Cogwheel (Relevant texture files are placed in `/assets/kubjs/textures/block`)
```javascript
// startup_scripts/demo.js
onEvent('block.registry', event => {
	event.create('example_cogwheel', 'cogwheel')
	    .material('wood')
	    .displayName('Example Cogwheel');
})
```
Example Adding A Large Cogwheel
```javascript
onEvent('block.registry', event => {
    event.create('large_example_cogwheel', 'large_cogwheel')
        .material('wood')
        .displayName('Example Cogwheel');
})
```