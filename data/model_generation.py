from typing import Final, Dict, List, Set
from dataclasses import dataclass
import json
import os

# Directories
DATA: Final[str] = "data/"        
RESOURCES: Final[str] = "models/"
OUTPUT: Final[str] = "output/"

MODID: Final[str] = "extendedgears"

# Materials
DEFAULT_MATERIAL = "spruce"
MATERIALS: set[str] = {""}

@dataclass
class JsonModel:
    credit: str
    parent: str
    texture_size: List[int]
    textures: Dict[str, str]

class JsonModelEncoder(json.JSONEncoder):
    def default(self, o):
        return o.__dict__

def get_model_schema(filename: str, datapath: str = RESOURCES) -> JsonModel:
    file_path: str = f"{datapath}{filename}.json"
    if not os.path.isfile(file_path):
        raise FileExistsError(f"Specified model type: {file_path} does not exist") 
    with open(file_path, "r") as f:
        return JsonModel(**json.loads(f.read()))

def get_materials(filename: str = "materials", datapath: str = DATA) -> set[str]:
    file_path: str = f"{datapath}{filename}.json"
    if not os.path.isfile(file_path):
        raise FileExistsError(f"Specified File: {file_path} does not exist") 
    with open(file_path, "r") as f:
        return set(json.loads(f.read()))

def is_default_material(material: str, default_material: str = DEFAULT_MATERIAL) -> bool:
    return material == default_material

def cogwheel_texture_dict(material: str, modid: str = MODID) -> Dict[str, str]:
    return {"1_2": f"{modid}:{material}_cogwheel", "particle": f"{MODID}:{material}_cogwheel"}

def generate_cogwheels(type:str ,output_path: str = OUTPUT, materials: set[str] = MATERIALS, ignore_default: bool = True) -> None:
    if not os.path.exists(output_path):
        os.makedirs(output_path)
    for material in materials:
        if ignore_default and is_default_material(material):
            continue
        generate_cogwheel(type, material, output_path)

def generate_cogwheel(type: str, material: str, output_path: str = OUTPUT) -> None:
    with open(f"{output_path}{type}_{material}_cogwheel.json", "w") as f:
        model: JsonModel = get_model_schema(f"{type}_cogwheel")
        model.textures = cogwheel_texture_dict(material)
        f.write(json.dumps(model, indent=4, cls=JsonModelEncoder))

def large_cogwheel_texture_dict(material: str, modid: str = MODID) -> Dict[str, str]:
    return {"0": "create:block/cogwheel_axis", "3": "create:block/axis_top", 
            "4": f"{modid}:large_{material}_cogwheel","particle": f"{modid}:large_{material}_cogwheel"}

def generate_large_cogwheels(type: str, output_path: str = OUTPUT, materials: set[str] = MATERIALS, ignore_default: bool = True) -> None:
    if not os.path.exists(output_path):
        os.makedirs(output_path)
    for material in materials:
        if ignore_default and is_default_material(material):
            continue
        generate_large_cogwheel(type, material, output_path)

def generate_large_cogwheel(type: str, material: str, output_path: str = OUTPUT) -> None:
    with open(f"{output_path}{type}_{material}_cogwheel.json", "w") as f:
        model: JsonModel = get_model_schema(f"{type}_cogwheel")
        model.textures = large_cogwheel_texture_dict(material)
        f.write(json.dumps(model, indent=4, cls=JsonModelEncoder))

def generate_small_and_large_cogwheels(name: str, materials: set[str] = MATERIALS) -> None:
    generate_cogwheels(name, materials=materials)
    generate_large_cogwheels(name, materials=materials)

if __name__ == '__main__':
    materials: Final[set[str]] = get_materials()
    types: set = {"default", "large", "shaftless", "large_shaftless", "half_shaft", "large_half_shaft"}
    for type in types:
        generate_small_and_large_cogwheels(type, materials)


