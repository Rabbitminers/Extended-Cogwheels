import json

with open('./data/data/types.json') as file_ref:
    types_data = json.load(file_ref)
with open('./data/data/materials.json') as file_ref:
    materials_data = json.load(file_ref)

cogwheels = []
for shape in types_data['shape']:
    for subtype in types_data['subtype']:
        for material in materials_data:
            id = '_'.join(filter(bool, (shape, subtype, material)))
            cogwheels.append(f'extendedgears:{id}_cogwheel')

with open('./data/data/cogwheel_ids.json', 'w') as file_ref:
    json.dump(cogwheels, file_ref, indent=2)