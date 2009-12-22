/*
 * <copyright>
 *  Copyright 2004-2005 (Jonatan Gomez Solutions JG-Sol)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the JML Open Source License as published by
 *  UN-Data Mining Group on the JML Open Source Website
 *  (http://dis.unal.edu.co/profesores/jgomez/projects/jml/index.htm).
 *
 *  THE JML SOFTWARE AND ANY DERIVATIVE SUPPLIED BY LICENSOR IS
 *  PROVIDED "AS IS" WITHOUT WARRANTIES OF ANY KIND, WHETHER EXPRESS OR
 *  IMPLIED, INCLUDING (BUT NOT LIMITED TO) ALL IMPLIED WARRANTIES OF
 *  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE, AND WITHOUT
 *  ANY WARRANTIES AS TO NON-INFRINGEMENT.  IN NO EVENT SHALL COPYRIGHT
 *  HOLDER BE LIABLE FOR ANY DIRECT, SPECIAL, INDIRECT OR CONSEQUENTIAL
 *  DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE OF DATA OR PROFITS,
 *  TORTIOUS CONDUCT, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 *  PERFORMANCE OF THE JML SOFTWARE.
 *
 * </copyright>
 */
package jml.object.sources;

import jml.random.Partition;
import jml.util.sort.MergeSort;


/**
 * <p>Title: PartitionObjectSource</p>
 * <p>Description: A class representing a partition process from a data source.
 * Useful for folding cross-validation analysis</p>
 * <p>Copyright:    Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class PartitionSource extends SamplingSource {

/**
 * The partition applied to the data set
 */
  protected Partition partition = null;

/**
 * Constructor: Creates a sampling object with marked data records
 * @param source Data source to be analized
 * @param partition Partition applied to the data set. I don't check if the partition
 * has the same size of the data set. I suppose is ok.
 * @param group The key group in the partition: For using it (testing) or discarding it (training)
 * @param useGroup If the key group is used (the iteration is on the group only)
 * or discarded (the iteration is done over the data set without including the group)
 */
  public PartitionSource(ObjectSource source, Partition partition,
                              int group, boolean useGroup) {
    super(source);
    this.partition = partition;
    init(group, useGroup);
  }

  /**
   * Inits the partition ObjectSource
   * @param group The key group in the partition: For using it (testing) or discarding it (training)
   * @param useGroup If the key group is used (the iteration is on the group only)
   * or discarded (the iteration is done over the data set without including the group)
   */
  public void init(int group, boolean useGroup) {
    if (useGroup) {
      index = partition.getGroup(group);
    } else {
      index = partition.skipGroup(group);
    }
    m = index.length;
    MergeSort sort = new MergeSort();
    sort.low2high(index);
  }
}
