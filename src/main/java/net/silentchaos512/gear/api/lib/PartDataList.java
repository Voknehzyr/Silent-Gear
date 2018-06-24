package net.silentchaos512.gear.api.lib;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.silentchaos512.gear.api.parts.*;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public class PartDataList implements List<ItemPartData> {

    private List<ItemPartData> list = new ArrayList<>();

    private PartDataList() {
    }

    public static PartDataList of(Collection<ItemPartData> c) {

        PartDataList ret = new PartDataList();
        ret.list.addAll(c);
        return ret;
    }

    public static PartDataList of(ItemPartData... parts) {

        PartDataList ret = new PartDataList();
        Collections.addAll(ret.list, parts);
        return ret;
    }

    public static PartDataList from(Collection<ItemStack> stacks) {
        PartDataList ret = new PartDataList();
        // Get part data for each stack, if it is a part. Silently ignore non-parts.
        stacks.stream().map(ItemPartData::fromStack).filter(Objects::nonNull).forEach(data -> ret.list.add(data));
        return ret;
    }

    public PartDataList getUniqueParts(boolean mainsOnly) {

        PartDataList result = PartDataList.of();
        for (ItemPartData data : (mainsOnly ? getMains() : list)) {
            if (result.stream().map(ItemPartData::getPart).noneMatch(part -> part == data.part)) {
                result.add(data);
            }
        }
        return result;
    }

    @Nullable
    public ItemPartData getPrimaryMain() {

        for (ItemPartData data : list)
            if (data.part instanceof ItemPartMain)
                return data;
        return null;
    }

    public List<ItemPartData> getMains() {

        return getParts(data -> data.part instanceof ItemPartMain);
    }

    public List<ItemPartData> getRods() {

        return getParts(data -> data.part instanceof ToolPartRod);
    }

    public List<ItemPartData> getTips() {

        return getParts(data -> data.part instanceof ToolPartTip);
    }

    public List<ItemPartData> getParts(Predicate<ItemPartData> predicate) {

        ImmutableList.Builder<ItemPartData> builder = ImmutableList.builder();
        for (ItemPartData data : list)
            if (predicate.test(data))
                builder.add(data);
        return builder.build();
    }

    @Override
    public boolean add(ItemPartData arg0) {

        return list.add(arg0);
    }

    @Override
    public void add(int arg0, ItemPartData arg1) {

        list.add(arg0, arg1);
    }

    @Override
    public boolean addAll(Collection<? extends ItemPartData> arg0) {

        return list.addAll(arg0);
    }

    @Override
    public boolean addAll(int arg0, Collection<? extends ItemPartData> arg1) {

        return list.addAll(arg0, arg1);
    }

    @Override
    public void clear() {

        list.clear();
    }

    @Override
    public boolean contains(Object arg0) {

        return list.contains(arg0);
    }

    @Override
    public boolean containsAll(Collection<?> arg0) {

        return list.containsAll(arg0);
    }

    @Override
    public ItemPartData get(int arg0) {

        return list.get(arg0);
    }

    @Override
    public int indexOf(Object arg0) {

        return list.indexOf(arg0);
    }

    @Override
    public boolean isEmpty() {

        return list.isEmpty();
    }

    @Override
    public Iterator<ItemPartData> iterator() {

        return list.iterator();
    }

    @Override
    public int lastIndexOf(Object arg0) {

        return list.lastIndexOf(arg0);
    }

    @Override
    public ListIterator<ItemPartData> listIterator() {

        return list.listIterator();
    }

    @Override
    public ListIterator<ItemPartData> listIterator(int arg0) {

        return list.listIterator(arg0);
    }

    @Override
    public boolean remove(Object arg0) {

        return list.remove(arg0);
    }

    @Override
    public ItemPartData remove(int arg0) {

        return list.remove(arg0);
    }

    @Override
    public boolean removeAll(Collection<?> arg0) {

        return list.removeAll(arg0);
    }

    @Override
    public boolean retainAll(Collection<?> arg0) {

        return list.retainAll(arg0);
    }

    @Override
    public ItemPartData set(int arg0, ItemPartData arg1) {

        return list.set(arg0, arg1);
    }

    @Override
    public int size() {

        return list.size();
    }

    @Override
    public List<ItemPartData> subList(int arg0, int arg1) {

        return list.subList(arg0, arg1);
    }

    @Override
    public Object[] toArray() {

        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] arg0) {

        return list.toArray(arg0);
    }
}
