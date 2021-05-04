# Platinum Terraform

Platinum Terraform is designed as an alternative building tool. Basic selection and edit commands are supported, but the power lies in Terraform Modes, which intelligently modify selected blocks based on the users's current mode.

Supported features:
- Terraform modes:
  - Replace - Change all blocks to the held block
  - Remove - Same as replace, but replaces with air instead
  - Fill - Fill holes with the option to vertically 'lock' the fill algorithm. Once the algorithm starts descending, it won't rise again, even to the height of the selected block.
  - Overlay - Detects the shape of the selected plane and lays another layer on top with the held block
  - Excavate - Detects the shape of the selected plane and digs equal to the radius option
  - Plane - Detects the shape of the selected plane and removes a layer
- Modes can have alternative options
  - Modify only blocks that share the same type as the selected block
  - All modes operate based on a chosen radius, which can be set while choosing a mode, or for all future modes until changed
- Supported selection commands
  - /Set - Set entire area to held block
  - /Replace - Like set, but only replaces solid blocks
  - /Box - Set all 6 sides to held block
  - /Outline - Set all 12 edges to held block
  - /Walls
  - /Floor
  - /Ceiling
