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
          price: req.body.price,
          odometer: req.body.odometer,
          title: req.body.title
        });
        const car = await newMaintenance.save();
        res.status(200).json(car);
        
    } catch (error) {
        console.log(error);
        res.status(500).send("Server error");
    }
})

router.get("/", async (req, res) => {
  let maintenance = await Maintenance.find();
  /*
  * we want to have the latest maintenance at the top, that's why we have a timestamp as well
  * for this we need to sort our maintenance array by date
  */
 maintenance.sort((a, b) => {
   // Turn your strings into dates, and then subtract them
   // to get a value that is either negative, positive, or zero.
   return new Date(b.timestamp) - new Date(a.timestamp);
 });
  res.json(maintenance);
});

router.delete("/:id", async (req,res) => {
    try {
        await Maintenance.findByIdAndDelete(req.params.id);
        res.json({ done: 1 });
    } catch (error) {
        console.log(error);
        res.status(500).send("Server error");
    }
})

router.put("/:id", async (req, res) => {
  try {
    let maintenance = await Maintenance.findOneAndUpdate(
      { _id: req.params.id },
      {
        date: req.body.date,
        shopName: req.body.shopName,
        description: req.body.description,
        place: req.body.place,
        price: req.body.price,
        odometer: req.body.odometer,
        title: req.body.title,
      }
    );
    res.json({ done: 1 });
  } catch (error) {
    console.log(error);
    res.status(500).send("Server Error");
  }
});

module.exports = router;