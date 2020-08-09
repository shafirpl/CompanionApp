//
//  NotesMainViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-08.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit

class NotesMainViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {

    @IBOutlet weak var notesTableView: UITableView!
    var notes = [noteItemStruct]()
    override func viewDidLoad() {
        super.viewDidLoad()


        downloadJSON {
            //print("Successful")
            /*
             * after downloading the data, this function will get executed, which in turn will
             * excute the two functions that we wrote below, the notes.count function and
             * initializing the row with title
             */
            self.notesTableView.reloadData()
        }
        
        notesTableView.delegate = self
        notesTableView.dataSource = self
    }
    // this basically tells us how many rows will be there
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return notes.count
    }
    // this basically initalizes every row with the title coming from our json array's objects
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: .default, reuseIdentifier: nil)
        cell.textLabel?.text = notes[indexPath.row].title.capitalized
        return cell
    }
    
    // now we need to pass the data to our screen where we have the stuff
    // in order to do so, we need to know which row was selected and
    // grab data from that cell and pass it
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        // the identifier is the one we defined when we connected
        // the screen to here
        // screenshot in the google doc
        
        // this will tell the app to go to the note details screen,
        // this won't pass the data
        performSegue(withIdentifier: "showDetails", sender: self)
        
    }
    
    // this function passes data to the details view controller
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destination = segue.destination as? NoteDetailsViewController{
            destination.noteItem = notes[(notesTableView.indexPathForSelectedRow?.row)!]
        }
    }
    
    /*
     * basically with the closure completed function, what will happen is that
     * we will pass a function when we are calling this function as a parameter, and
     * it will execute this function after the download is done
     * so we could do downloadJSON{print("Something")}, and after
     * the download is complete, it will print "something" on the console
     */
    func downloadJSON(completed: @escaping () -> ()){
        let url = URL(string: "http://138.68.61.175:5500/notes")
        URLSession.shared.dataTask(with: url!) { (data, response, error) in
            if error == nil{
                do{
                    self.notes = try JSONDecoder().decode([noteItemStruct].self, from: data!)
                    DispatchQueue.main.async {
                        completed()
                    }
                    
                } catch{
                    print("Json error")
                }
                
            }
        }.resume()
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
