const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const MaintenanceSchema = new Schema({
  title: {
    type: String,
  },
  timestamp: {
    type: Date,
    default: Date.now
  },
  odometer: {
    type: Number,
  },
  date: {
    type: String,
    required: true,
  },
  shopName:{
      type: String,
  },

  place: {
    type: String,
  },
  price: {
    type: Number,
  },
  description: {
    type: String,
  },
});

module.exports = Notes = mongoose.model("maintenance", MaintenanceSchema);
