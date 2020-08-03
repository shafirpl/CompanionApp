const express = require("express");
const router = express.Router();
const Maintenance = require("../models/maintenance");

router.post("/", async (req,res) => {
    try {
        const newMaintenance = new Maintenance({
          date: req.body.date,
          shopName: req.body.shopName,
          description: req.body.description,
          place: req.body.place,
          price: req.body.price
        });
        const car = await newMaintenance.save();
        res.status(200).send(car);
        
    } catch (error) {
        console.log(error);
        res.status(500).send("Server error");
    }
})

router.get("/", async (req, res) => {
  const maintenance = await Maintenance.find();
  res.json(maintenance);
});

router.delete("/:id", async (req,res) => {
    try {
        await Maintenance.findByIdAndDelete(req.params.id);
        res.status(200).send("deleted");
    } catch (error) {
        console.log(error);
        res.status(500).send("Server error");
    }
})

router.put("/:id", async (req, res) => {
  try {
    let maintenance = await Maintenance.findOneAndUpdate(
      { id: req.params.id },
      {
        date: req.body.date,
        shopName: req.body.shopName,
        description: req.body.description,
        place: req.body.place,
        price: req.body.price,
      }
    );
    res.json({ done: 1 });
  } catch (error) {
    console.log(error);
    res.status(500).send("Server Error");
  }
});

module.exports = router;