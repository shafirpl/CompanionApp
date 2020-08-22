//
//  PcTempMainTableViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-17.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON

class PcTempMainTableViewController: UITableViewController {
    let url = "http://138.68.61.175:5500/ip"
    var records: [ipAddressStruct] = [ipAddressStruct]()
    @IBOutlet var pcListTableView: UITableView!
    var editingMode:Bool = false
    var selectedRow:Int!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.records.removeAll()
        fetchRecords()
        pcListTableView.delegate = self
        pcListTableView.dataSource = self

    }
    
    
    override func viewDidAppear(_ animated: Bool) {
        self.records.removeAll()
        fetchRecords()
    }
    
    
    func goToEditController(){
        self.editingMode = true
        self.performSegue(withIdentifier: "pcItemIdentifier", sender: self)
        /*
        * this is very important, if we don't set it to false, after we go to edit mode once
        * it will always be true. So after we go to edit mode and then we click the plus button
        * the plus button will take us to the edit mode, as we didn't set it to false
        * I made a youtube video explaining why i had to do this.
        */
        self.editingMode = false
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // for confirming stuff
        if let destination = segue.destination as? AddNewPCViewController{
            if(editingMode){
                destination.editingMode = editingMode
                let record:ipAddressStruct = records[selectedRow]
                /*
                * for some reason this doesn't work, I guess since when we swipe to right or left
                * we don't explicitly select the row so the code doesn't know which row
                * was selected, unlike the second if else part which gets triggered
                * only when a row is selected, which performs a seague to that view
                * controller triggering the else if part, in that part the code
                * knows which row was selected since it only gets triggered when the row
                * is selected
                */
                // let record = records[(pcListTableView.indexPathForSelectedRow?.row)!]
               // print(record)
                destination.sourceCompName = record.pcName
                destination.sourceIpAddress = record.ipAddress
                destination.id = record.id
            }
            
        }
        else if let destination = segue.destination as? PcTempDetailsViewController{
            let record:ipAddressStruct = records[(pcListTableView.indexPathForSelectedRow?.row)!]
            destination.ipAddress = record.ipAddress
            destination.givenName = record.pcName
        }
    }

    
    // this is for showing edit button on swipe from left to right
    override func tableView(_ tableView: UITableView, leadingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        let edit = UIContextualAction(style: .normal, title: "Edit") { (contextualAction, view, actionPerformed: (Bool) -> ()) in
            self.selectedRow = indexPath.row
            self.goToEditController()
            actionPerformed(true)
        }
        return UISwipeActionsConfiguration(actions: [edit])
    }
    

    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        performSegue(withIdentifier: "showTempDetails", sender: self)
    }
    
    
    // swipe to delete functinality,look at NotesMainViewController to see details
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if (editingStyle == .delete){
            showAlert(indexPath: indexPath)
        }
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return records.count
    }
    
    func showAlert(indexPath:IndexPath){
        let alert = UIAlertController(title: "Are You Sure", message: "This Action is Permanent", preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Yes", style: .destructive, handler: {
            action in
            self.deleteNote(recordId: self.records[indexPath.row].id ?? "", indexPath: indexPath)
        }))
        alert.addAction(UIAlertAction(title: "No", style: .cancel, handler: nil))
        
        present(alert, animated: true)
    }
    
    func deleteNote(recordId:String, indexPath:IndexPath){
        let urlString = url + "/" + recordId
        AF.request(urlString,method: .delete).responseJSON{
            response in print(response)
        }
        records.remove(at: indexPath.row)
        pcListTableView.deleteRows(at: [indexPath], with: .fade)
    }
    
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "PcTempTableViewCell") as! PcTempTableViewCell
        cell.compNameLabel?.text = records[indexPath.row].pcName?.capitalized
        cell.ipAddressLabel?.text = records[indexPath.row].ipAddress?.capitalized
        return cell
    }

    func fetchRecords(){
        AF.request(url,method: .get).responseJSON(completionHandler: {
            respone in
            let data = JSON(respone.data!)
            // print(data)
            /*
            * when we run a for each loop on json array, we get an index and json
            * object. Uncomment the loop and see how the data look like
            * so we need two things, an int var to capture the index, and an
            * object to capture the json object, thats why our main loop
            * looks like this
            */
            //  for record in data {
            //     print(record)
            //  }
            for (_,obj) in data{
                var record: ipAddressStruct = ipAddressStruct()
                record.id = obj["_id"].stringValue
                record.pcName = obj["compName"].stringValue
                record.ipAddress = obj["ipAddress"].stringValue
                self.records.append(record)
            }
            self.pcListTableView.reloadData()
        })
        
    }


}
