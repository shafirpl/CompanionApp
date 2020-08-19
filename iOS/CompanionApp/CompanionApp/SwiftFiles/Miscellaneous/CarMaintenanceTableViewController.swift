//
//  CarMaintenanceTableViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-13.
//  Copyright © 2020 shafirpl. All rights reserved.
//

/*
 * look at the NotesMainViewController to get an idea what each part of
 * the code is doing. Only difference is that we use alamofire to get the request
 * and in struct we use var instead of let
 */

import UIKit
import Alamofire
import SwiftyJSON


class CarMaintenanceTableViewController: UITableViewController {
    @IBOutlet var maintenanceTableView: UITableView!
    
    let url: String = "http://138.68.61.175:5500/maintenance"
    var records = [maintenanceItemStruct]()
    override func viewDidAppear(_ animated: Bool) {
        records.removeAll()
        fetchRecords()
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        // self.tableView.isMultipleTouchEnabled = false
        fetchRecords()
        maintenanceTableView.delegate = self
        maintenanceTableView.dataSource = self
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
        maintenanceTableView.deleteRows(at: [indexPath], with: .fade)
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

    /*
     * https://www.youtube.com/watch?v=kYmZ-4l0Yy4
     * watch from 7:00 mark
     */
    
    // this basically initalizes every row with the title coming from our json array's objects
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
//        let cell = UITableViewCell(style: .default, reuseIdentifier: "maintenanceItemCell") as! MaintenanceItemTableViewCell
        let cell = tableView.dequeueReusableCell(withIdentifier: "maintenanceItemCell") as! MaintenanceItemTableViewCell

        cell.title?.text = records[indexPath.row].title?.capitalized
        cell.date?.text = records[indexPath.row].date
        
        return cell
    }

    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        performSegue(withIdentifier: "recordDetails", sender: self)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destination = segue.destination as? RecordDetailsViewController{
            destination.recordItem = records[(maintenanceTableView.indexPathForSelectedRow?.row)!]
        }
    }



    func fetchRecords(){
        AF.request(url, method: .get).responseJSON(completionHandler: {
            response in
            let data = JSON(response.data!)
            // print(data)
            /*
             * when we run a for each loop on json array, we get an index and json
             * object. Uncomment the loop and see how the data look like
             * so we need two things, an int var to capture the index, and an
             * object to capture the json object, thats why our main loop
             * looks like this
             */
//            for record in data {
//                print(record)
//            }
            
            for (_,obj) in data{
                var record:maintenanceItemStruct = maintenanceItemStruct()
                record.shopName = obj["shopName"].stringValue
                record.date = obj["date"].stringValue
                record.description = obj["description"].stringValue
                record.title = obj["title"].stringValue
                record.price = obj["price"].doubleValue
                record.odometer = obj["odometer"].intValue
                record.place = obj["place"].stringValue
                record.id = obj["_id"].stringValue
                // print(record)
                self.records.append(record)
            }
            print(self.records)
            self.maintenanceTableView.reloadData()
        })

    }

}
