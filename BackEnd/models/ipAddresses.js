const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const IpAddressSchema = new Schema({
  compName: {
    type: String,
  },
  ipAddress:{
      type:String,
  }

});

module.exports = ipAddress = mongoose.model("ipAddress", IpAddressSchema);
