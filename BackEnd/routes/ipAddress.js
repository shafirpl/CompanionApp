const express = require("express");
const router = express.Router();
const ipAddress = require("../models/ipAddresses");

router.post("/", async (req,res) => {
    try {
        const newIpAddress = new ipAddress({
            compName: req.body.compName,
            ipAddress: req.body.ipAddress,
        });
        const ip = await newIpAddress.save();
        res.status(200).json(ip);
    } catch (error) {
        console.log(error);
        res.status(500).send("Server error");
    }

})

router.get("/", async (req,res) => {
    let ipAddresses = await ipAddress.find();
    res.json(ipAddresses);
})

router.delete("/:id", async (req, res) => {
  try {
    await ipAddress.findByIdAndDelete(req.params.id);
    res.json({ done: 1 });
  } catch (error) {
    console.log(error);
    res.status(500).send("Server error");
  }
});

router.put("/:id", async (req,res) => {
    try {
        let address = await ipAddress.findOneAndUpdate(
          { _id: req.params.id },
          {
            compName: req.body.compName,
            ipAddress: req.body.ipAddress,
          }
        );
        res.json({ done: 1 });
    } catch (error) {
        console.log(error);
        res.status(500).send("Server Error");
    }
})

module.exports = router;
