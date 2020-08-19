//
//  PcTempDetailsViewController.swift
//  CompanionApp
//
//  Created by shafi on 2020-08-17.
//  Copyright © 2020 shafirpl. All rights reserved.
//

import UIKit
import Alamofire
import SwiftyJSON

class PcTempDetailsViewController: UIViewController {

    @IBOutlet weak var cpuView: UIView!
    @IBOutlet weak var memView: UIView!
    @IBOutlet weak var infoView: UIView!
    var ipAddress: String?
    var url:String = "http://"
    var givenName:String?
    
    var fetchTimer:Timer?
    @IBOutlet weak var cpuUsageBar: UIProgressView!
    @IBOutlet weak var cpuUsageLabel: UILabel!
    @IBOutlet weak var cpuFreeLabel: UILabel!
    @IBOutlet weak var cpuModelLabel: UILabel!
    
    
    @IBOutlet weak var memUsageBar: UIProgressView!
    @IBOutlet weak var memUsageLabel: UILabel!
    @IBOutlet weak var memFreeLabel: UILabel!
    @IBOutlet weak var totalMemLabel: UILabel!
    
    @IBOutlet weak var originalNameLabel: UILabel!
    @IBOutlet weak var givenNameLabel: UILabel!
    @IBOutlet weak var ipAddressLabel: UILabel!
    
    
    /*
     * TODO: Ask question why we don't need viewDidAppear here, it seems like
     * we don't need viewDidAppear here
     */
    override func viewDidLoad() {
        super.viewDidLoad()
        fetchTimer = Timer.scheduledTimer(timeInterval: 2, target: self, selector: #selector(fetchStatus), userInfo: nil, repeats: true)
        url += self.ipAddress!+":4200/socket"
        stylingView(myView: cpuView)
        stylingView(myView: memView)
        stylingView(myView: infoView)
    }
    
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(true)
        fetchTimer?.invalidate()
    }
    
    func stylingView(myView:UIView){
        /*
         * https://stackoverflow.com/questions/51095450/how-to-apply-card-view-cornerradius-shadow-like-ios-appstore-in-swift-4
         */
        // myView.layer.cornerRadius = 20.0
        myView.layer.shadowColor = UIColor.gray.cgColor
        myView.layer.shadowOffset = CGSize(width: 0.0, height: 0.0)
        myView.layer.shadowRadius = 12.0
        myView.layer.shadowOpacity = 0.7
        //padding
        // myView.layoutMargins = UIEdgeInsets(top: 8, left: 16, bottom: 8, right: 16);
    }

    

    @objc func fetchStatus(){
        //let url:String = "http://192.168.0.189:4200/socket"
        AF.request(url,method: .get).responseJSON(completionHandler: {
            response in
            let data = JSON(response.data!)
            print(data)
            //print(data.count)
            // if the computer is offline, data.count will be 0
            if (data.count > 0){
                var progressStr:String = data["cpuUsage"].stringValue
                print(progressStr)
                //progressStr = progressStr.trimmingCharacters(in: .whitespacesAndNewlines)
                /*
                 * https://stackoverflow.com/questions/24122288/remove-last-character-from-string-swift-language
                 */
                /*
                 * we need to remove the stuff twice, as we get something like "0.8 %"
                 * so first we need to remove the % and then the white space
                 */
                progressStr.remove(at: progressStr.index(before: progressStr.endIndex))
                progressStr.remove(at: progressStr.index(before: progressStr.endIndex))
                print(progressStr)
                let progress:Float = Float(progressStr) ?? 0.0
                print(progress)
                /*
                 * it seems like the progress bar takes a value from 0.0 to 1.0, so we need
                 * to divide our stuff by 100
                 */
                self.cpuUsageBar.progress = (progress/100)
                
                self.cpuFreeLabel.text = data["cpuFree"].stringValue
                self.cpuUsageLabel.text = data["cpuUsage"].stringValue
                self.cpuModelLabel.text = data["cpuModel"].stringValue
                
                
                let memFullProgressStr = data["memUsage"].stringValue
                // https://developer.apple.com/documentation/swift/substring
                let endOfSentence = memFullProgressStr.firstIndex(of: "|")!
                var memProgressStr = memFullProgressStr[...endOfSentence]
                print(memProgressStr)
                memProgressStr.removeLast()
                memProgressStr.removeLast()
                memProgressStr.removeLast()
                //print(memProgressStr)
                let memProgress = Float(memProgressStr) ?? 0.0
                self.memUsageBar.progress = (memProgress/100)
                self.memFreeLabel.text = data["memFree"].stringValue
                self.memUsageLabel.text = data["memUsage"].stringValue
                self.totalMemLabel.text = (data["totalMem"].stringValue)+"GB"
                
                self.originalNameLabel.text = data["compName"].stringValue
                self.givenNameLabel.text = self.givenName
                self.ipAddressLabel.text = self.ipAddress
                
            }
        })
    }
    

}
