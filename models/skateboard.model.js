const mongoose = require("mongoose");

var skateboardSchema = new mongoose.Schema({
  ownerName: {
    type: String,
    required: "Name of the owner is required."
  },
  brand: {
    type: String
  },
  weight: {
    type: Number
  },
  length: {
    type: Number
  },
  location: {
    type: String,
    required: "Location is required."
  },
  isAvailable: {
    type: Boolean
  }
});

mongoose.model("Skateboard", skateboardSchema);
