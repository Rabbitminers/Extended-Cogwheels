from typing import Final
import re
import os
import glob

CREATE: Final[str] = "create"
ID: Final[str] = "extendedgears"
RECIPE_PATH: Final[str] = "recipes"
OUTPUT_PATH: Final[str] = "recipes/output"

def get_all_files(namespace: str, filetype: str = "json") -> list[str]:
    cwd: str = os.getcwd()
    return glob.glob(f'{cwd}/{RECIPE_PATH}/{namespace}/**/*.{filetype}', recursive=True)

def get_all_files_using_item(files: list[str], itemtype: str, namespace: str = CREATE) -> list[str]:
    query: str = f"\"item\": \"{namespace}:{itemtype}\""
    return [file for file in files if os.path.isfile(file) and query in open(file).read()]

def get_all_files_using_tag(files: list[str], tag: str, namespace: str = CREATE) -> list[str]:
    query: str = f"\"tag\": \"{namespace}:{tag}\""
    return [file for file in files if os.path.isfile(file) and query in open(file).read()]

def replace_item_with_tag(filepath: str, item: str, item_namespace: str, tag: str, tag_namespace: str = ID) -> None:
    query: str = f"\"item\": \"{item_namespace}:{item}\""
    replace_value: str = f"\"tag\": \"{tag_namespace}:{tag}\""
    content: str = ""
    with open(filepath, "r") as f:
        content = f.read()
    new_content = re.sub(query, replace_value, content)
    if new_content == content:
        return
    cwd: str = os.getcwd()
    new_file_path: str = f"{OUTPUT_PATH}{filepath}"
    if not os.path.exists(new_file_path.rsplit('/', 1)[0]):
        os.makedirs(new_file_path.rsplit('/', 1)[0])
    with open(new_file_path, "w") as f:
        f.write(new_content)

def replace_all_items_in_file_with_tag(files: list[str], item: str, item_namespace: str, tag: str, tag_namespace: str = ID) -> None:
    for file in files:
        replace_item_with_tag(file, item, item_namespace, tag, tag_namespace)

if __name__ == '__main__':
    files: list[str] = get_all_files(CREATE)
    print(files)
    large_cogwheel_files: list[str] = get_all_files_using_item(files, "large_cogwheel", CREATE)
    cogwheel_files: list[str] = get_all_files_using_item(files, "cogwheel", CREATE)

    replace_all_items_in_file_with_tag(large_cogwheel_files, "large_cogwheel", CREATE, "large_cogwheel", ID)
    replace_all_items_in_file_with_tag(cogwheel_files, "cogwheel", CREATE, "small_cogwheel", ID)