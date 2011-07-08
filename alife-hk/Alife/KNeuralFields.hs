module Alife.KNeuralFields 
       (
         
       ) where

import Alife.MathUtil
import Alife.NeuralFields
import Alife.NeuralFieldsInput

kDelta :: (RealFrac a) => a -> (a, Int) -> [a] -> a -> [a] -> [a]
kDelta tao partI wa x pa = noDelayDelta tao [1] wa (vectorCode partI x) pa   

solveKDelta h tao (max,n) wa x = solve delta [0.0 | k<-[1..n]] h 
  where delta = kDelta tao (max,n) wa x 
 
   