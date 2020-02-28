const express = require("express");
var router = express.Router();
const mongoose = require("mongoose");
const Skateboard = mongoose.model("Skateboard");

router.get("/", (req, res) => {
  res.render("skateboard/addOrEdit", {
    viewTitle: "Add Skateboard"
  });
});

router.post("/", (req, res) => {
  if (req.body._id == "") insertRecord(req, res);
  else updateRecord(req, res);
});

function updateRecord(req, res) {
  Skateboard.findOneAndUpdate(
    { _id: req.body._id },
    req.body,
    { new: true },
    (err, doc) => {
      if (!err) {
        res.redirect("skateboard/list");
      } else {
        if (err.name == "ValidationError") {
          handleValidationError(err, req.body);
          res.render("skateboard/addOrEdit", {
            viewTitle: "Update Skateboard",
            skateboard: req.body
          });
        } else console.log("Error during record update : " + err);
      }
    }
  );
}

function insertRecord(req, res) {
  var skateboard = new Skateboard();
  skateboard.ownerName = req.body.ownerName;
  skateboard.brand = req.body.brand;
  skateboard.weight = req.body.weight;
  skateboard.length = req.body.length;
  skateboard.location = req.body.location;
  skateboard.save((err, doc) => {
    if (!err) {
      res.redirect("skateboard/list");
    } else {
      if (err.name == "ValidationError") {
        handleValidationError(err, req.body);
        res.render("skateboard/addOrEdit", {
          viewTitle: "Add Skateboard",
          skateboard: req.body
        });
      } else {
        console.log("Error during record duration: " + err);
      }
    }
  });
}

router.get("/list", (req, res) => {
  Skateboard.find((err, docs) => {
    if (!err) {
      console.log(docs);
      res.render("skateboard/list", {
        list: docs[0].ownerName
      });
    } else {
      console.log("Error in retrieving skateboard list :" + err);
    }
  });
});

function handleValidationError(err, body) {
  for (field in err.errors) {
    switch (err.errors[field].path) {
      case "ownerName":
        body["ownerNameError"] = err.errors[field].message;
        break;
      case "email":
        body["emailError"] = err.errors[field].message;
        break;
      default:
        break;
    }
  }
}

router.get("/:id", (req, res) => {
  Skateboard.findById(req.params.id, (err, doc) => {
    if (!err) {
      res.render("skateboard/addOrEdit", {
        viewTitle: "Update Skateboard",
        skateboard: doc
      });
    }
  });
});

router.get("/delete/:id", (req, res) => {
  Skateboard.findByIdAndRemove(req.params.id, (err, doc) => {
    if (!err) {
      res.redirect("/skateboard/list");
    } else {
      console.log("Error in skateboard delete :" + err);
    }
  });
});

module.exports = router;
